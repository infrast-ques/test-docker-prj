package example.test.controller

import example.test.exceptions.NotFoundException
import example.test.models.Item
import example.test.repo.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("message")
open class MessageController @Autowired constructor(
        private val itemRepository: ItemRepository
) {

    @GetMapping
    open fun list() = itemRepository.let {
        val items = it.findAll().apply { forEach { it.viewed++ } }
        it.saveAll(items)
        items.sortedBy { it.id }
    }


    @GetMapping("{id}")
    open fun getMessage(@PathVariable id: Long) = itemRepository.let {
        val item = it.findById(id).orElseThrow { NotFoundException }
        it.save(item.apply { viewed++ })
    }


    @PostMapping
    open fun create(@RequestBody message: String) {
        itemRepository.save(Item(text = Item.deserialize(message).text))
    }

    @PutMapping("{id}")
    open fun update(@PathVariable id: Long, @RequestBody message: String) = itemRepository.let {
        val item = it.findById(id).orElseThrow { NotFoundException }
        item.apply { text = Item.deserialize(message).text }
        it.save(item)
    }

    @DeleteMapping("{id}")
    open fun delete(@PathVariable id: Long) = itemRepository.let {
        if (it.existsById(id)) {
            it.deleteById(id)
        } else {
            throw NotFoundException
        }
    }
}