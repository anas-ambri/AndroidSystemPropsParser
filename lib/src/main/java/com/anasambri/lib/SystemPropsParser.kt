package com.anasambri.lib

import java.util.regex.Pattern

class SystemPropsParser {
    companion object {
        fun parse(props: String): Map<String, Any> {
            val map = mutableMapOf<String, Any>()
            props.reader().readLines().forEach {  propLine ->
                if (!propLine.trim().isEmpty()) {
                    val systemProp = SystemProp.parse(propLine.trim())
                    var mapTree : MutableMap<String, Any> = map
                    systemProp.keyPath().forEachIndexed { index, key ->
                        if (index == systemProp.keyPath().size - 1) {
                            mapTree[key] = systemProp.value()
                        } else {
                            if (!mapTree.containsKey(key)) {
                                mapTree[key] = mutableMapOf<String, Any>()
                            }
                            if (mapTree[key] is String) {
                                val temp = mapTree[key]!!
                                mapTree[key] = mutableMapOf<String, Any>().apply {
                                    set("", temp)
                                }
                            }
                            mapTree = mapTree[key] as MutableMap<String, Any>
                        }
                    }
                }
            }
            return map
        }
    }
}

class SystemProp(key: List<String>, value: String) {

    private val pair = Pair(key, value)

    fun keyPath() = pair.first
    fun value() = pair.second

    companion object {
        private val pattern = Pattern.compile("^\\[(.+)]:\\s\\[(.*)]$")

        fun parse(propLine: String): SystemProp {
            val matcher = pattern.matcher(propLine)
            if (!matcher.matches()) {
                throw IllegalArgumentException("Parameter $propLine does not match the format: "+ pattern.toString())
            }
            return SystemProp(matcher.group(1).split("."), matcher.group(2))
        }
    }
}
