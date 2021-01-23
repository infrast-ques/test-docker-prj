package example.test.models


import com.google.gson.Gson
import javax.persistence.*

@Entity
@Table(name = "items")
class Item(
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = -1,
        @Column(name = "text")
        var text: String? = null,
        @Column(name = "viewed")
        var viewed: Int = 0
) {
    companion object {
        fun deserialize(source: String) = Gson().apply { serializeNulls() }.fromJson(source, Item::class.java)
    }

    override fun toString() = Gson().apply { serializeNulls() }.toJson(this)
}
