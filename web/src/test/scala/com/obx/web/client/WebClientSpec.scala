package com.obx.web.client

import utest._

import org.scalajs.jquery.jQuery

object WebClientSpec extends TestSuite {

  WebClient.setupUI()

  def tests = TestSuite {

    'HelloWorld {
      assert(jQuery("p:contains('Hello World')").length == 1)
    }
    
  }
  
}
