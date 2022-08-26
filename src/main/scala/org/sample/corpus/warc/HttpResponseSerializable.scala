package org.sample.corpus.warc

import java.io.Serializable
import org.apache.hc.core5.http.ClassicHttpResponse
import org.apache.hc.core5.http.message.BasicClassicHttpResponse

/** Seritalizable wrapper of ClassicHttpResponse. */
class HttpResponseSerializable(resp: ClassicHttpResponse, val body: Array[Byte])
    extends Serializable {
  def this() = this(new BasicClassicHttpResponse(600), Array.empty[Byte])

  /** Returns the value of the first header with the given name.
    *
    * @throws ProtocolException
    *   in case multiple headers with the given name are found.
    */
  def getHeader(name: String): Option[String] = {
    Option(resp.getHeader(name)).map(_.getValue)
  }

  def getFirstHeader(name: String): Option[String] = {
    Option(resp.getFirstHeader(name)).map(_.getValue)
  }

  def getLastHeader(name: String): Option[String] = {
    Option(resp.getLastHeader(name)).map(_.getValue)
  }

  def getHeaders(): Seq[(String, String)] = {
    resp.getHeaders().map(header => (header.getName(), header.getValue()))
  }
}
