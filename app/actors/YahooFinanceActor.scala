package actors

import play.api.Logger
import play.api.libs.json.{JsObject, JsString, Json}
import yahoofinance.YahooFinance

object YahooFinanceActor {

  private val logger = Logger(this.getClass)


  /**
    * Custom implementation of the yahoo finance  api to create and return a JSON object
    * that will provide the front end with the necessary display information
    * @param stockTicker
    * @return
    */
  def getStockAttributes(stockTicker:String) : JsObject={
    logger.info(s"attributes requested for stock: $stockTicker")

    val stockPrice = YahooFinance.get(stockTicker).getQuote(true).getPrice().toPlainString
    //val stockName =  YahooFinance.get(stockTicker).getName()
    var stockChange = "neutral"
    if(YahooFinance.get(stockTicker).getQuote(true).getChange().doubleValue()>0){
      stockChange = "pos"
    }else if(YahooFinance.get(stockTicker).getQuote(true).getChange().doubleValue()<0){
      stockChange = "neg"
    }

    val response = Json.obj(
      "stockObj" -> Json.obj(
        "name" -> stockTicker,
        "price" -> stockPrice,
        "change" -> stockChange
      )
    )

    val classification =
      if (stockChange.equals("neutral"))
        "neutral"
      else if (stockChange.equals("neg"))
        "neg"
      else
        "pos"

    val r = response + ("label" -> JsString(classification))
    logger.info(s"response = $r")

    r
  }

}
