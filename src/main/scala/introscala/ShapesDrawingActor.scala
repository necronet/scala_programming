package introscala.shapes

object Message {
    object Exit
    object Finished
    case class Response(message: String)
}

import akka.actor.Actor

class ShapeDrawingActor extends Actor {

    import Message._
    // Partial function [Any, Unit]
    def receive = {
        case s: Shape =>
            s.draw( str => println(s"ShapeDrawingActor: $str"))
            sender ! Response(s"ShapeDrawingActor: $s drawn")
        case Exit => 
            println("ShapesDrawingActor: exiting...")
            sender ! Finished
        case unexpected =>
            val response = Response(s"ERROR: Unknonw message $unexpected")
            println(s"ShapeDrawingActor $response")
            sender ! response
    }

}
