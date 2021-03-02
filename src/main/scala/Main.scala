import java.io.File

/**
 * types in scala
 *
 */

object Main extends App {
  
  object Config { 
    val GST = BigDecimal(5/100)
  } 

  case class CostSheet(
                        val costs: Seq[CostSheetValue]
                      ) {
    def getTotalCost(): BigDecimal = costs.map(it => it.amount).reduce((a, b) => a + b)
  }

  case class CostSheetValue(
                             val descripton: String,
                             val percentageOfAgreementValue: BigDecimal,
                             val amountNoTax: BigDecimal,
                             val tax: BigDecimal = Config.GST
                           ) {
    
    def amount = amountNoTax + amountNoTax * (tax)
  }

  case class AppartmentFee(
                            val agreementValue: BigDecimal,
                            val registrationFee: BigDecimal,
                            val tdsFee: BigDecimal,
                            val socityFee: BigDecimal,
                            val misceleneous: BigDecimal,
                          ) {

    def totalCost = agreementValue + registrationFee + tdsFee + socityFee + misceleneous

  }


  private val costSheet = Seq(
    CostSheetValue(descripton = "sample1", amountNoTax = BigDecimal("1000"),
      percentageOfAgreementValue = 10),
    CostSheetValue(descripton = "sample2", amountNoTax = BigDecimal("1000"),
      percentageOfAgreementValue = 10),
    CostSheetValue(descripton = "sample3", amountNoTax = BigDecimal("1000"),
      percentageOfAgreementValue = 10),
    CostSheetValue(descripton = "sample4", amountNoTax = BigDecimal("1000"),
      percentageOfAgreementValue = 10),
  )

  private val sheet: CostSheet = CostSheet(costs = costSheet)
  
  println(s"this is the total cost: ${sheet.getTotalCost()}")

}
