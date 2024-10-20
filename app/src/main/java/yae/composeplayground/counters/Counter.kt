package yae.composeplayground.counters

data class Counter(val id:Int, val title:String, val count:Int) {
    fun copyIncremented() = copy(count=this.count+1)
}

fun MutableList<Counter>.replaceById(id:Int, replacer:(Counter)->Counter) {
    val idx = this.indexOfFirst { it.id ===id }
    if (idx >=0) {
        this[idx] = replacer(this[idx])
    }
}