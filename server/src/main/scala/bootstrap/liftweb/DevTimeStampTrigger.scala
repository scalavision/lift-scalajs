
package bootstrap.liftweb

import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.{Paths, Files, StandardOpenOption}
import java.nio.charset.{StandardCharsets}
import net.liftweb.common.Loggable

object DevTimeStampTrigger extends Loggable{
  val fileSystem = FileSystems.getDefault()
  val separator = fileSystem.getSeparator()
  val currentDir = new java.io.File(".").getCanonicalPath()
  
  def write(fileName:String, contents:String, filePath:String = currentDir):Unit = {
    logger.info("writing timestamp to: " + filePath + " " + fileName)
    Files.write(Paths.get(filePath + separator + fileName), contents.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE)
  }   
  
}
