package com.tfandkusu.learndi

import android.graphics.Bitmap

open class Action


/**
 * リロードがリクエストされた
 */
class RefreshRequestAction: Action()

/**
 * 名刺情報読み込み完了アクション
 */
class CardLoadAction(val card: Card) : Action()

class CardImageLoadAction(val bitmap: Bitmap) : Action()