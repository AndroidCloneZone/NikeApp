package com.project.clonecoding.nike.common.data

sealed class CardType{
    data object MasterCard: CardType()
    data object MaestroCard: CardType()
    data object VisaCard: CardType()
    data object JcbCard: CardType()
    data object AmexCard: CardType()
    data object DinersclubCard: CardType()
    data object UnionpayCard: CardType()
    data object DiscoverCard: CardType()
}