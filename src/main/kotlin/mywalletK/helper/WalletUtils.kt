package mywalletK.helper

import com.google.common.util.concurrent.FutureCallback
import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import org.bitcoinj.core.TransactionConfidence

object WalletUtils {

    @JvmStatic
    fun trasactionConfidence(confidence:TransactionConfidence){
        println("Number of peers that have received this broadcast is ${confidence.numBroadcastPeers()}")
        if (confidence.confidenceType == TransactionConfidence.ConfidenceType.BUILDING) {
            //set confirmed
            println("tx is confirmed based on confidence type")
        } else {
            val future: ListenableFuture<TransactionConfidence> = confidence.getDepthFuture(1)
            Futures.addCallback(future, object : FutureCallback<Any?> {
                override fun onFailure(t: Throwable) {
                    t.printStackTrace()
                    println(t.message)
                    throw RuntimeException(t)
                }

                override fun onSuccess(result: Any?) {
                    println("tx is confirmed from the Listenable future")
                }
            }, MoreExecutors.directExecutor())
        }
    }
}
