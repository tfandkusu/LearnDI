package com.tfandkusu.learndi

open class Action


/**
 * リロードがリクエストされた
 */
class RefreshRequestAction: Action()

/**
 * 名刺情報読み込み完了アクション
 */
class CardLoadAction(val card: Card) : Action()