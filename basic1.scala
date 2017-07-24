package Multithreading
/*in this we will concentrate on multicore environment 
 * 
 * 
 */
object basic1 extends App {
  def fib(n:Int):Int=if(n<2) 1 else fib(n-1)+fib(n-2)
  for(i<-(30 to 15 by -1).par){
    println(fib(i))
  }
  
 //race condition examples 
  var i=0
  for(j<- 0 to 10000)
  {
    i+=1
    println(i)
  }
  //parallelism that creates a race condition 
 /*loads i from memory increment it and store to memory 
  * in multi thread multiple threads take it and does the same operation
  * 
  */
  for(j<- (0 to 10000).par)
  {
    i+=1
    println(i)
  }
  //in the above we introduced randonmess of threads to overcome that we need to synchronize
  
}