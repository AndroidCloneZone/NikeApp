package com.project.clonecoding.nike.common

import com.project.clonecoding.nike.common.data.CardType
import java.util.regex.Pattern

object CardRegex {
    private const val MASTER_CARD_REGEX = "^((22(?!20)[2-9][0-9]|2[3-6][0-9]{2}|27[0-1][0-9]|2720))$"
    private const val MAESTRO_CARD_REGEX = "^(3[47][0-9]{2})$"
    private const val VISA_CARD_REGEX = "^(35(2[8-9]|[3-8][0-9]))$"
    private const val JCB_CARD_REGEX = "^(36[0-9]{2})$"
    private const val AMEX_CARD_REGEX = "^(4[0-9]{3})$"
    private const val DINERSCLUB_CARD_REGEX = "^(5[1-5][0-9]{2})$"
    private const val UNIONPAY_CARD_REGEX = "^(50[0-9]{2}|5[6-9][0-9]{2})$"
    private const val DISCOVER_CARD_REGEX = "^(60(?!60)[0-9]{2}|61[0-9]{2}|64[0-9]{2})$"


    /**
     * 카드 번호에 따라 어떤 카드 종류인지 반환한다
     * @param cardNumber 카드 번호
     * @return 카드 종류 반환, nullable
     */
    fun getMatchedCardType(cardNumber: String): CardType?{
        if(cardNumber.length < 4) return null
        val cn = if(cardNumber.length > 4) cardNumber.substring(0 until 4) else cardNumber

        return when{
            Pattern.matches(MASTER_CARD_REGEX, cn) -> CardType.MasterCard
            Pattern.matches(MAESTRO_CARD_REGEX, cn) -> CardType.MaestroCard
            Pattern.matches(VISA_CARD_REGEX, cn) -> CardType.VisaCard
            Pattern.matches(JCB_CARD_REGEX, cn) -> CardType.JcbCard
            Pattern.matches(AMEX_CARD_REGEX, cn) -> CardType.AmexCard
            Pattern.matches(DINERSCLUB_CARD_REGEX, cn) -> CardType.DinersclubCard
            Pattern.matches(UNIONPAY_CARD_REGEX, cn) -> CardType.UnionpayCard
            Pattern.matches(DISCOVER_CARD_REGEX, cn) -> CardType.DiscoverCard
            else -> null
        }
    }

    /**
     * 카트 종류에 따른 번호 자리수를 반환한다
     * @param cardType 카드 종류, nullable
     * @return 자리수 반환, nullable
     */
    fun getCardTypeNumberDigit(cardType: CardType?): Int{
        return when(cardType){
            CardType.AmexCard -> 15
            CardType.DinersclubCard -> 14
            else -> 16
        }
    }

    /**
     * 카드 종류에 따라 포메팅 된 카드 번호 형식을 반환한다
     */
    fun getFormattedCardNumber(cardNumber: String, cardType: CardType? = null): String{
        val str = StringBuilder()
        val numLength = cardNumber.length

        when(cardType){
            // Amex: xxxx-xxxxxxx-xxxxx
            // DinersclubCard: xxxx-xxxxxxx-xxxx
            // 앞부분인 xxxx-xxxxxx 형식은 동일하므로, 4-6-[4,5] 형식으로 처리될 수 있도록 함
            CardType.AmexCard, CardType.DinersclubCard -> {
                if(numLength <= 4){
                    str.append(cardNumber.substring(0 until numLength))
                }else{
                    str.append(cardNumber.substring(0 until 4))
                    str.append("-")
                    if(numLength <= 10){
                        str.append(cardNumber.substring(4 until numLength))
                    }else{
                        str.append(cardNumber.substring(4 until 10))
                        str.append("-")
                        str.append(cardNumber.substring(10 until numLength))
                    }
                }
            }
            // xxxx-xxxx-xxxx-xxxx
            else -> {
                str.append(cardNumber.chunked(4).joinToString("-"))
            }
        }

        return str.toString()
    }
}