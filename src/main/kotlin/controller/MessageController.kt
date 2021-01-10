package controller

import exceptions.DataAlreadyExistException
import exceptions.NotFoundException
import models.ModelItem
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("massage")
class MessageController {

    private var counter: Int = 4
    private var messages = mutableListOf(ModelItem(id = "1", text = "one"), ModelItem(id = "2", text = "two"))

    @GetMapping
    fun list() = messages

    @GetMapping("{id}")
    fun getMessage(@PathVariable id: String) = messages.find { it.id == id }?.toString() ?: throw NotFoundException

    @PostMapping
    fun create(@RequestBody message: String) = messages.apply {
        ModelItem.deserialize(message).let { modelItem ->
            if (!any { it.id == modelItem.id }) {
                counter++
                add(modelItem)
            } else {
                throw DataAlreadyExistException
            }
        }
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: String) = messages.apply { remove(find { it.id == id }) }
}
