# 오답노트
You are managing a retail inventory system where products are stored in a MongoDB collection called inventory. Each product document has fields _id, item, quantity, and price. You need to increase the quantity of a product if it exists or insert a new product with a specific quantity and price if it does not exist. The product is identified by the item field. Which of the following updateOne commands should you use to accomplish this?
---
db.inventory.updateOne(
  { item: "notebook" },
  { $inc: { quantity: 10 }, $setOnInsert: { price: 5.99 } },
  { upsert: true }
)

setOnInsert -> set the price only when a new document is inserted.
upsert
