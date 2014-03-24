package com.geeksville.dapi.model

import com.github.aselab.activerecord.Datestamps
import com.github.aselab.activerecord.annotations._
import org.squeryl.annotations.Transient
import org.mindrot.jbcrypt.BCrypt
import java.util.UUID
import com.github.aselab.activerecord.dsl._

/**
 * A vehicle model
 *
 * @param uuid is selected by the client on first connection
 */
case class Vehicle(@Required @Unique uuid: UUID = UUID.randomUUID()) extends DapiRecord {
  /**
   * Who owns me?
   */
  lazy val user = belongsTo[User]
  val userId: Option[Long] = None

  /**
   * A user specified name for this vehicle (i.e. my bixler)
   */
  var name: String = ""

  /**
   * All the missions this vehicle has made
   */
  lazy val missions = hasMany[Mission]

  // Vehicle manufacturer if known, preferably from the master vehicle-mfg.txt definitions file.  
  // To add new definitions to the file, please submit a github pull-request.
  var manufacturer: Option[String] = None

  // Vehicle type if known, preferably from the master vehicle-types.txt definitions file.  
  // To add new definitions to the file, please submit a github pull-request.
  var vehicleType: Option[String] = None

  // Autopilot type if known, preferably from the master autopilot-types.txt definitions file.
  // To add new definitions to the file, please submit a github pull-request.
  var autopilotType: Option[String] = None

  // Autopilot software version #
  var softwareVersion: Option[String] = None
}

object Vehicle extends DapiRecordCompanion[Vehicle] {
  /**
   * Find by ID but using a string encoding (i.e. UUID or somesuch)
   * For now I just convert the int to its base-10 representation
   */
  def find(id: UUID): Option[Vehicle] = this.where(_.uuid === id).headOption
}