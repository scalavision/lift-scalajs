package com.obx.web.client

/*
 * Basic usage of the logging functionality
 * 
 * log.warn("Application starting")
   log.enableServerLogging("/logging")
   log.info("this message goes to server")
    
 */

package object logger {
  private val defaultLogger = LoggerFactory.getLogger("Log")

  def log = defaultLogger
}
