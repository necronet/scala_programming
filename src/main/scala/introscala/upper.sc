// object refer to a singleton in Scala
object Upper {
    def upper(strings: String*) = strings.map(_.toUpperCase())
}

println(Upper.upper("Hello","World"))