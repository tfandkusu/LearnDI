package com.tfandkusu.learndi

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

/**
 * 16:9で画像を表示するView
 */
class HDImageView : View {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    /**
     * Viewの横幅
     */
    private var viewWidth = 0

    /**
     * Viewの縦幅
     */
    private var viewHeight = 0

    var bitmap: Bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
        set(value) {
            invalidate()
            field = value
        }

    private var paint = Paint()

    private val src = Rect()

    private val dst = RectF()


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //横幅を取得する
        viewWidth = MeasureSpec.getSize(widthMeasureSpec)
        //縦幅を横幅の9 / 16にする
        viewHeight = viewWidth * 9 / 16
        setMeasuredDimension(viewWidth, viewHeight)
    }


    override fun onDraw(canvas: Canvas) {
        paint.isAntiAlias = true
        paint.isFilterBitmap = true
        // 描画元
        src.left = 0
        src.top = 0
        src.right = bitmap.width
        src.bottom = bitmap.height
        // 表示スケール
        val scale = min(viewWidth / bitmap.width.toFloat(), viewHeight / bitmap.height.toFloat())
        // 描画先
        dst.left = viewWidth.toFloat() / 2 - bitmap.width * scale / 2
        dst.top = viewHeight.toFloat() / 2 - bitmap.height * scale / 2
        dst.right = viewWidth.toFloat() / 2 + bitmap.width * scale / 2
        dst.bottom = viewHeight.toFloat() / 2 + bitmap.height * scale / 2
        // 描画する
        canvas.drawBitmap(bitmap, src, dst, paint)
    }
}