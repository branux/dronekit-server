package com.geeksville.doarama

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import org.json4s.Formats
import org.json4s.DefaultFormats
import org.json4s._
import org.json4s.native.JsonMethods._
import org.json4s.native.Serialization
import java.io.File
import grizzled.slf4j.Logging
import org.scalatest.GivenWhenThen
import scala.util.Random
import java.io.BufferedOutputStream
import java.io.FileOutputStream

class DoaramaTests extends FunSuite with Logging with GivenWhenThen {

  test("upload and get view URL") {
    val userId = "test-bob"
    val client = new DoaramaClient(userId)

    val igc = getClass.getResourceAsStream("short.igc")
    val activity = client.uploadIGC(igc)
    assert(activity >= 0)

    client.setActivityInfo(activity)
    val vizid = client.createVisualization(activity)
    assert(vizid >= 0)

    val url = client.getDisplayURL(vizid)
    warn("View URL is " + url)
  }
}