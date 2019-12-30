import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

def sleep(millis: Long) = {
    Thread.sleep(millis)
}

def doWork(index: Int) = {
    sleep((Math.random() * 1000).toLong)
    index
}

(1 to 5) foreach { index =>

val future = Future {
    doWork(index)
}

future onSuccess {
    case a: Int => println(s"Success! returned $a")
}

future onFailure {
    case th: Throwable => println(s"FAILUREE! returneed $th")
}

}

sleep(1000)
println("Done")