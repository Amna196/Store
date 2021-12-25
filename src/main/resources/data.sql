insert into Category values(1,'Crew Neck style','It is characterized by a round, circular neckline that fits snugly at the neck.','https://www.google.com/url');
insert into Category values(2, 'V Neck style','this type of t-shirt forms a V shape at the neck.','https://www.google.com/url?sa=i&url');
insert into Category values(3, 'Plain t shirt style','Plain t-shirts are the staple of a man’s wardrobe. They are called classic for a reason after all.','https://www.google.com/url');
insert into Category values(4, 'Raglan sleeve types','which are available in three quarter and full- length sleeves.','https://www.google.com/url');
insert into Category values(5, 'Hooded style','Its athleisure appeal is perfect for the gym travel or while playing football with your homies.','https://www.google.com/url');

insert into Brand values(1,'nike','nike is cool','https://www.google.com/imgres');
insert into Brand values(2,'ellese','ellese is nice','https://www.google.com/url');
insert into Brand values(3,'adidas','adidas is nice','https://www.google.com/url');
insert into Brand values(4,'twan','twan is great','https://www.google.com/url');
insert into Brand values(5,'puma','puma is cool','https://www.google.com/url');

--insert into Product values(1,'SportTop','First', select * from Category where iD=0 ,'Crew Neck style' ,select * from Brand where iD=0,'nike', false, true, true, 'https://cdn.tamanna.com/assets/foot_locker/nike/317344050080_01.jpg?quality=80&format=auto&width=1000', 'white', 19.750);
--insert into Brand values(2, 'SportTop','Second',  categories.get(1), categories.get(1).getTitle(), brands.get(1), brands.get(1).getTitle(), true, false, true, "https://www.yoox.com/images/items/12/12518052WC_14_f.jpg?impolicy=crop&width=387&height=490", "black", 25.0));
--insert into Brand values(3, 'SportTop','Third',  categories.get(2),categories.get(2).getTitle(),  brands.get(2), brands.get(2).getTitle(), true, true, true, "https://adidasm2-cdn.revton.com/media/catalog/product/cache/840x840/1706b311b4e139861e83dd948758a3de/h/3/h37756-10.jpg", "red", 15.0));
--insert into Brand values(4,'Classic Top','Fourth',  categories.get(3), categories.get(3).getTitle(), brands.get(3), brands.get(3).getTitle(), false, true, true, "https://cdn-images.farfetch-contents.com/15/27/32/97/15273297_27082626_1000.jpg", "pink", 22.0));
--insert into Brand values(5,'SportTop','Fifth',  categories.get(0), categories.get(4).getTitle(), brands.get(0), brands.get(4).getTitle(), true, true, true,"https://static.aawweb.com/media/catalog/product/cache/9f18371e3a457e456c922dbc54690d4f/1/c/1c03b57c2fd58a611a0f2bc09c97e2fca28e789fee447edec90831ec5a09e899.jpeg", "gray", 10.500));
