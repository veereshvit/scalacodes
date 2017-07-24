package Multithreading
import scala.concurrent.Future
import scala.util.Failure
import scala.util.Success
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
class transaction(private var _balance:Int) {
  def balance=_balance
  def deposit(amt:Int):Boolean=this.synchronized{
    if(amt<0) false 
    else{
    _balance+=amt
 true
    }
  }
  def withdraw(amt:Int):Int={
    var a=0
    if(amt<0||amt<_balance){
     a
    }
    else{  a=_balance-amt
      a      
    }
  }
}
//in parallel collection we get differentes deposit 
//in single thread we get 500500
//to make paraallel work to get exact result synchronize the deposit meethod
//in this.synchronized it sets a lock and makes the thread to fully access
//and makes other thread to wait till finished cons it is slower
//hence need of future and actors over sync leads to deadlock
object BankAcc extends App{
  val acc=new transaction(0)
  for(i<-(0 to 10000).par)
    {acc.deposit(i)
    }
    println(acc.balance)
 var cnt=0

 var v=Future {
      for(i<-0 to 100000) cnt+=1}.foreach{_=>println("f1 done")}
v=Future {
      for(i<-0 to 100000) cnt+=1}.foreach{_=>println("f2 done")}


Thread.sleep(1000)
println(cnt)
//until i dont add the thread.sleep in the bottom count will not be published
Thread.sleep(1000)

}


