package com.tfandkusu.learndi

/**
 * ローカルに保持したデータ
 * @param value データ。存在しなければnull。
 * @param time サーバから取得した時刻
 */
data class LocalData<T>(val value: T?, val time: Long)