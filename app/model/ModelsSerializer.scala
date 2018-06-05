package model

import play.api.libs.json.{Reads, Writes}

trait ModelsSerializer[T] {
  val reads: Reads[T]
  val writes: Writes[T]
}
