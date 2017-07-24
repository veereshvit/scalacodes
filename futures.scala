package Multithreading
import scala.concurrent.Future
import scala.util.Failure
import scala.util.Success
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
object futures extends App {
println("before the future")
def fib(n:Int):Int=if(n<2) 1 else fib(n-1)+fib(n-2)
//Anthing in the future gets executed on a different thread
  val f=Future{
    println("in the future to print it ")

  }
  //the above program runs and dont print anything reason main thread run and never gets the future to run 
Thread.sleep(1)//100th of a second 
println("after the future")
  //readLine()
    //our intent is now to make calculation 
val f2=Future{
  for(i<-(0 to 30).par){
    println(fib(i))
    //implicitly forcing for the runtime exception
    //throw new RuntimeException("something went bad")
  }
}
f2.foreach(print)
f2.onComplete{
  case Success(n)=>println(n)
  case Failure(exec)=>println(exec)
}
//readLine()
//Awaiting for futures to be completed
//referred to as blocking on thread 
//result usage not to call directly done by Await.result
//if we dont passs the time then it leads to dead lock where multiple threads are waiting for the resoource
println(Await.result(f2,4.seconds))
/*firstcompletedof-->traversable why do we want to create mutiple futures and
   *  wait for completion of one 
   * if data is coming from different sources and 
   * you wait for one to complete then we make use of firstcompletedof
   *  
   * seq traversable of futures->future(traversable)
   *  its like sequence of futures and collection of futures as op
   * 
   */
val page1=Future{
  "Google"+io.Source.fromURL("http:://www.google.com").take(200).mkString
}
val page2=Future{
  "facebook"+io.Source.fromURL("http:://www.facebook.com").take(200).mkString
}
val page3=Future{
  "twitter"+io.Source.fromURL("http:://www.twitter.com").take(200).mkString
}

val page=List(page1,page2,page3)
val firstpage=Future.firstCompletedOf(page)
firstpage.foreach(println)
//to get the information of all of them 
val futureseqpages=Future.sequence(page)
futureseqpages.foreach(println)
Thread.sleep(5000)
}