package example

import com.google.api.core.ApiFutures
import com.google.api.core.ApiFuture
import com.google.api.core.ApiFutureCallback
import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.CountDownLatch

fun fA(): ApiFuture<String> {
  Thread.sleep(3000)
  return ApiFutures.immediateFuture("A")
}

fun fB(): ApiFuture<String> {
  Thread.sleep(4000)
  return ApiFutures.immediateFuture("B")
}

class Example {
  companion object {
      @JvmStatic
      fun main(args: Array<String>) {
        var t = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Date())
        println("[$t] Main function")

        val executors: ExecutorService = Executors.newCachedThreadPool()
        val latch = CountDownLatch(1)
        
        ApiFutures.addCallback(
          ApiFutures.transformAsync(
            fA(),
            { a: String ->
              t = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Date())
              println("[$t] fA -> $a")
              fB()
            },
            executors
          ),
          object: ApiFutureCallback<String> {
            override fun onSuccess(result: String){
              t = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Date())
              println("[$t] fA -> $result")
              latch.countDown()
              executors.shutdown()
            }
            override fun onFailure(e: Throwable){
              e.printStackTrace()
            }
          }, executors)
        
          latch.await()
          t = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Date())
          println("[$t] Finished.")
      }
  }
}