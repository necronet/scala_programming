def calcTax(amount: Float)(implicit rate: Float): Float = amount * rate

object SimpleStateSaleTax {
    implicit val rate: Float =  0.05F
}

case class ComplicatedSalesTaxData(baseRate: Float, isTaxHoliday: Boolean, storeId: Int)

object ComplicatedSalesTax {
    private def extraTaxRateForStore(id: Int): Float = {
        0.0F
     }

     implicit def rate(implicit cstd: ComplicatedSalesTaxData): Float = {
         if (cstd.isTaxHoliday) 0.0F
         else cstd.baseRate + extraTaxRateForStore(cstd.storeId)
     }
}
/* Using a very straighforward implicit rate value coming from a singleton object */
{
    import SimpleStateSaleTax.rate
    val amount = 100F
    println(s"Tax on $amount = ${calcTax(amount)} rate: $rate")
}
/* Using a very a whole class that has an implicit parameter to calculate an implicit rate */
{
    import ComplicatedSalesTax.rate
    implicit val myStore = ComplicatedSalesTaxData(0.06f, false, 1010)
    val amount = 100F
    println(s"Tax on $amount = ${calcTax(amount)} rate: $rate")
}

/* Using a very a whole class that has an implicit parameter to calculate an implicit rate for holidays */
{
    import ComplicatedSalesTax.rate
    implicit val myStore = ComplicatedSalesTaxData(0.06f, true, 1010)
    val amount = 100F
    println(s"Tax on $amount = ${calcTax(amount)} rate: $rate")
}