insert into app_user (id, email, password, profile_image_url, username)
values ('dcb9483c-db19-4c03-b914-75a7d6e10282',
        'jeremiah@clark.com',
        '$2a$10$UfUiwNZb5xvByP8HkHYZ7uEkGCMF9L2SueHcz1Z/Khu2EFKKTULHO',
        NULL,
        'jeremiahclark'),
       ('3f903ecb-8087-4cdf-ad46-95314a000a17',
        'filip@kisic.com',
        '$2a$10$D7C7WMYrisf9HazWv.vtAujvUfZ0MnitqRNxzVAPAt8O3ktEsmiu',
        NULL,
        'filipkisic'),
       ('9333f76b-af67-4909-9a82-32ced2b6eb15',
        'saul@hudson.com',
        '$2a$10$8756P4V0wvuaQxg00cIj08.heBczcYSncP7X61hWbiVaraC8EXgSM6',
        NULL,
        'slash'),
       ('02305266-c083-4796-b3a7-520737fe2005',
        'hames@muy.com',
        '$2a$10$JRgp05zWmKzkrok,n5n2Ruq6an9iSufg10YwF8g18P.fwGvL4U/7m',
        NULL,
        'hanesmuy'),
       ('41997705-1017-4605-a479-dcc0216dc417',
        'rikardo@hamod.com',
        '$2a$10$Jsc8JvLHmoD0bzRh1U59w06qF1Z6gzKSftfuBpul0jQR6UekrW0y2',
        NULL,
        'rikardohanod'),
       ('bddcb906-c0c8-4a1b-8bb5-e0d0df6cc651',
        'matte@blanco.com',
        '$2a$10$e7X/FqsOttNEVQg7351M5ewlklYadbFGxsTWsCcxaDyC6qkXHPC2C',
        NULL,
        'matteblanco');

insert into blog (id, title, content, thumbnail_image_url, create_date, number_of_likes, category, user_id)
values ('b6507f13-9936-4ef0-93d1-9f73bb5fcedb',
        'The Future of Electric Vehicles',
        'The automotive industry is rapidly shifting towards electric vehicles (EVs), with advancements in battery technology, charging infrastructure, and autonomous driving. These innovations are not just transforming how we drive, but also how we think about the environment and sustainability.',
        'https://www.netcarshow.com/MG-ZS_Hybrid-2025-1280-8f1072f01e9c7f8cfaeab36bc03ccb8fa4.jpg',
        '2024-08-31 10:00:00+00',
        0,
        'TECHNOLOGY',
        '3f903ecb-8087-4cdf-ad46-953f4a000a17');

insert into blog (id, title, content, thumbnail_image_url, create_date, number_of_likes, category, user_id)
values ('fa2a3b1e-4cb5-4c59-bd28-ef214ae2b3a5',
        'Breaking: Major Automaker Announces New Hydrogen-Powered Vehicle',
        'In a surprising move, a leading automaker has announced the launch of a new hydrogen-powered vehicle, aiming to rival the growing electric vehicle market. This marks a significant development in the pursuit of alternative fuels and could reshape the future of automotive energy.',
        'https://i.ytimg.com/vi/Ppuvvr6rcb0/maxresdefault.jpg',
        '2024-08-30 14:30:00+00',
        0,
        'HOT_NEWS',
        '3f903ecb-8087-4cdf-ad46-953f4a000a17');

insert into blog (id, title, content, thumbnail_image_url, create_date, number_of_likes, category, user_id)
values ('0fcdbb1f-4fdc-4a47-9a18-c69f339b589b',
        'The Timeless Charm of Classic Cars',
        'Classic cars continue to captivate enthusiasts with their timeless design, craftsmanship, and the nostalgia they evoke. From the curves of a vintage Ferrari to the elegance of a 1960s Mustang, these vehicles are more than just cars; they are pieces of art on wheels.',
        'https://www.netcarshow.com/Mercedes-Benz-SL-Class-1971-1280-1a0aa47d17995bf9c4b4182a00672e8544.jpg',
        '2024-08-29 09:15:00+00',
        0,
        'OLDTIMER',
        'dcb9483c-db19-4c03-b9f4-75a7d6e1d282');

insert into blog (id, title, content, thumbnail_image_url, create_date, number_of_likes, category, user_id)
values ('8c4a9eeb-77b4-4947-a8d7-1b5b7894b92c',
        'Exploring the World of Exotic Supercars',
        'Exotic supercars represent the pinnacle of automotive engineering, with their powerful engines, sleek designs, and unparalleled performance. From Lamborghini to Bugatti, these cars are designed to thrill, turning heads wherever they go.',
        'https://d1i1eo6qmdfmdv.cloudfront.net/upload/site/pages/newslider_otherpages/slider/EXR_FERRARI_ALL_SUPERCARS_SLIDERS_0004_1.jpg',
        '2024-08-28 11:45:00+00',
        0,
        'EXOTIC',
        'e23052b6-c083-4796-b3a7-52e737fe2e05');

insert into blog (id, title, content, thumbnail_image_url, create_date, number_of_likes, category, user_id)
values ('2b9a4c26-4a44-4f4d-badb-8b9edb9b4a7a',
        'Automaker Unveils Revolutionary Self-Driving Car',
        'A major automaker has just unveiled its latest self-driving car, equipped with cutting-edge AI and sensor technology. This marks a significant leap forward in autonomous driving, promising safer and more efficient transportation in the near future.',
        'https://dda.ndus.edu/ddreview/wp-content/uploads/sites/18/2021/10/selfDriving.png',
        '2024-08-27 08:30:00+00',
        0,
        'TECHNOLOGY',
        'e23052b6-c083-4796-b3a7-52e737fe2e05');

insert into blog (id, title, content, thumbnail_image_url, create_date, number_of_likes, category, user_id)
values ('6f34a9e2-b726-4f88-87d4-efd9475e3fd3',
        'Groundbreaking Battery Tech Extends EV Range',
        'A new breakthrough in battery technology is set to extend the range of electric vehicles by up to 30%. This innovation could be a game-changer for the EV market, addressing one of the main concerns of potential buyers.',
        'https://images.tayna.com/prod-images/1200/Powerline/065-powerline-45-435.jpg',
        '2024-08-02 15:20:00+00',
        0,
        'TECHNOLOGY',
        '3f903ecb-8087-4cdf-ad46-953f4a000a17');

insert into blog (id, title, content, thumbnail_image_url, create_date, number_of_likes, category, user_id)
values ('0e7bd5a8-27a9-432f-920e-1efed7a6d8f6',
        'Breaking News: Major Recall on Electric Vehicles',
        'Several major automakers have issued a recall on a range of electric vehicles due to concerns over potential battery overheating. This recall affects thousands of vehicles worldwide and has raised questions about the safety of current EV technology.',
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRnhrk7PWAtz_HrY7VLA9_08DH7NsaN2xW6xA&s',
        '2024-07-15 13:10:00+00',
        0,
        'HOT_NEWS',
        'dcb9483c-db19-4c03-b9f4-75a7d6e1d282');

insert into blog (id, title, content, thumbnail_image_url, create_date, number_of_likes, category, user_id)
values ('b38c06b4-593a-4a4c-b6f3-df8bdf6c321c',
        'New Emission Standards Shake Up the Auto Industry',
        'Newly announced emission standards are set to have a massive impact on the automotive industry, pushing manufacturers to accelerate their shift towards cleaner energy vehicles. This development is expected to have wide-ranging effects on vehicle design and production.',
        'https://static.euronews.com/articles/stories/06/80/92/08/1200x675_cmsv2_9c54e623-2817-505a-be18-a079737aa894-6809208.jpg',
        '2024-06-24 17:45:00+00',
        0,
        'HOT_NEWS',
        'bddcb906-c0c8-4a1b-8bb5-e0d0df6cc651');

insert into blog_likes (blog_id, user_id)
values ('b6507f13-9936-4ef0-93d1-9f73bb5fcedb',
        'dcb9483c-db19-4c03-b9f4-75a7d6e1d282');

insert into blog_likes (blog_id, user_id)
values ('b6507f13-9936-4ef0-93d1-9f73bb5fcedb',
        'e23052b6-c083-4796-b3a7-52e737fe2e05');

insert into blog_likes (blog_id, user_id)
values ('8c4a9eeb-77b4-4947-a8d7-1b5b7894b92c',
        '3f903ecb-8087-4cdf-ad46-953f4a000a17');

insert into blog_likes (blog_id, user_id)
values ('0e7bd5a8-27a9-432f-920e-1efed7a6d8f6',
        '3f903ecb-8087-4cdf-ad46-953f4a000a17');

update blog
set number_of_likes = 2
where id = 'b6507f13-9936-4ef0-93d1-9f73bb5fcedb';

update blog
set number_of_likes = 1
where id = '8c4a9eeb-77b4-4947-a8d7-1b5b7894b92c';

update blog
set number_of_likes = 1
where id = '0e7bd5a8-27a9-432f-920e-1efed7a6d8f6';