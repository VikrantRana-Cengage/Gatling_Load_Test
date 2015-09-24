package cbs_load_test
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import io.gatling.core.Predef.doSwitch
import io.gatling.core.Predef.exec
import io.gatling.core.Predef.rampUsers
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef.stringToExpression
import io.gatling.core.Predef.tsv
import io.gatling.core.Predef.value2Expression
import io.gatling.core.scenario.Simulation
import io.gatling.jms.Predef.jms
import io.gatling.jms.Predef.jmsProtocolBuilder2jmsProtocol
import io.gatling.jms.Predef.jmsRequestBuilder2ActionBuilder

class addActivity extends Simulation {
  
//******************http protocol configuration & Headers definition**********************

  val basePath = System.getProperty("basePath", "http://coursebuilder.dev.cengage.info/services/coursebuilder/macro/course/VHTY21FDKMX7Q30L3004");
    val httpConf = http
    .baseURL(basePath)
    .maxConnectionsPerHost(10)
    .contentTypeHeader(HttpHeaderValues.ApplicationJson)
    .acceptHeader(HttpHeaderValues.ApplicationJson)
    .doNotTrackHeader("1")
    .connection("keep-alive")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")
    
    

//******************Scenario section started****************************** 
  
   val scn = scenario("add Activity")
            .exec("basepath")
           .body(StringBody("""{ {
    "addActivityToCourse": {
        "deployEnvironment": "dev",
        "location": {
            "navigationId": "SMCBQ0KAN7F4YB1JH378"
      
        },
        "options": {
            "casAdfId": "IA_newCas_01",
            "description": "Ish Testing01_Activity_int01",
            "dueDate": 1433476800000,
            "gradable": true,
            "isTimed": false,
            "manuallyGraded": false,
            "maxScore": 100,
            "maxTakes": 4,
            "startDate": 1433476800000,
            "title": "Add_Activity_Testing_Ish01_int",
            "type": "assessment"
        }
    }
} }""")).asJSON
            
       
 //******************Scenario section started****************************** 

setUp(
    scn.users(10).ramp(30).protocolConfig(httpConf)
)
}
  
