package introscala.shapes

import akka.actor.{Props, Actor, ActorRef, ActorSystem}
import com.typesafe.config.ConfigFactory

private object Start

object ShapesDrawingDriver {

    def main(arg: Array[String]) {
        val system = ActorSystem("DrawingActorSystem", ConfigFactory.load())
        val drawer = system.actorOf(Props(new ShapeDrawingActor), "drawingActor")
        val driver = system.actorOf(Props(new ShapesDrawingDriver(drawer)), "drawingServoce")

        driver ! Start
    }
}

class ShapesDrawingDriver(drawerActor: ActorRef) extends Actor {
import Message._

def receive = {
    case Start => 
        drawerActor ! Circle(Point(0.0, 0.0), 1.0)
        drawerActor ! Rectangle(Point(0.0, 0.0), 2, 5)
        drawerActor ! 3.1415
        drawerActor ! Triangle(Point(0.0, 0.0), Point(2.0, 0.0), Point(0.0, 3.0))
        drawerActor ! Exit
    case Finished =>
        println(s"ShapesDrawingDriver: cleaning up...,")
        context.system.terminate()
    case response: Response =>
        println("ShapesDrawingDriver:  Response = " + response)
    case unexpected =>
        println(s"ShapesDrawingDriver: ERROR: Received an unexpected message = $unexpected")
}

}