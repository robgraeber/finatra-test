package com.effinggames.util

import com.github.mustachejava.{DefaultMustacheFactory, Mustache, MustacheFactory}
import com.google.inject.Provides
import com.twitter.finatra.http.internal.marshalling.mustache.ScalaObjectHandler
import com.twitter.finatra.http.modules.DocRootModule
import com.twitter.finatra.http.routing.FileResolver
import com.twitter.inject.TwitterModule
import java.io._
import javax.inject.Singleton

import com.twitter.inject.annotations.Flag

/**
  * Custom mustache module, changes the template dir to "views/".
  */
object CustomMustacheModule extends TwitterModule {
  private val templatesDir = "views"
  override def modules = Seq(DocRootModule)

  @Provides
  @Singleton
  def provideMustacheFactory(
    resolver: FileResolver,
    @Flag("local.doc.root") localDocRoot: String): MustacheFactory = {
    // templates are cached only if there is no local.doc.root
    val cacheMustacheTemplates = localDocRoot.isEmpty

    new DefaultMustacheFactory(templatesDir) {
      setObjectHandler(new ScalaObjectHandler)

      override def compile(name: String): Mustache = {
        if (cacheMustacheTemplates) {
          super.compile(name)
        } else {
          new LocalFilesystemDefaultMustacheFactory(templatesDir, resolver).compile(name)
        }
      }
    }
  }
}

/**
  * A local filesystem-only MustacheFactory. Uses the FileResolver for resolution and
  * does not internally cache templates.
  */
private final class LocalFilesystemDefaultMustacheFactory(
  templatesDirectory: String,
  resolver: FileResolver)
  extends DefaultMustacheFactory {
  setObjectHandler(new ScalaObjectHandler)

  override def getReader(resourceName: String): Reader = {
    // Relative paths are prefixed by the templates directory.
    val filepath = if (resourceName.startsWith("/")) {
      resourceName
    } else if (templatesDirectory.startsWith("/")) {
      s"$templatesDirectory/$resourceName"
    } else {
      s"/$templatesDirectory/$resourceName"
    }

    (resolver.getInputStream(filepath) map { inputStream: InputStream =>
      new InputStreamReader(inputStream)
    }).getOrElse(throw new FileNotFoundException(s"Unable to find file: $filepath"))
  }
}
