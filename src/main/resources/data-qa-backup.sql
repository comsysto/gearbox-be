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

insert into blog (id, title, content, thumbnail_image_url, create_date, number_of_likes, category, user_id)
values ('3588bba5-d8da-4a36-ad79-b487e71970bb',
        'It even takes down real track tools',
        'When a child is on the way, the sports car often has to make way for a rational Pampersmobile. Good news for sports drivers: "Rational" is a very flexible term. M GmbH has long demonstrated just how flexible it is. Sports cars like the M3 and M4 are already much more suitable for everyday use than purebred sports cars. The M3 Touring is now an even more everyday treat that is as popular as beer and chicken at Oktoberfest. 510 biturbo horsepower are now celebrating the Nordschleife. Third, fourth, fifth gear - the S58 inline six-cylinder engine, already known from its brothers, is also celebrating in the G81. The eight-speed automatic transmission, which is standard in the M3 Touring, skilfully covers up the slight dip in torque at low revs in everyday use. The S58 is particularly fascinating on the race track with power up to a maximum rev level of 7,200 rpm, which is quite high for a turbo engine. The M3 Touring storms over the Quiddelbacher Höhe at 221 km/h, briefly takes the airfield section at 162 km/h before sprinting towards Schwedenkreuz at 264 km/h. Traction, driving stability, steering precision - the M3 Touring has inherited these genes from its M3/M4 brothers. Despite being more suitable for everyday use and offering the best driving comfort of all current M3/M4s, the driving dynamics DNA is unmistakably BMW M. It is outstanding in everyday life, but also shines with lateral dynamics.',
        'https://imgr1.auto-motor-und-sport.de/image-R1100x619-ffffff-C-fa74aed0-2158830.jpeg',
        '2025-01-31 08:22:23+00',
        0,
        'HOT_NEWS',
        'e17fffc8-e339-49b6-bc21-9c46b568ed43'); -- Filip Kisic blog

insert into blog_likes (blog_id, user_id)
values ('3588bba5-d8da-4a36-ad79-b487e71970bb', -- Track tools blog
        'e17fffc8-e339-49b6-bc21-9c46b568ed43'); -- Filip Kisic

insert into blog_likes (blog_id, user_id)
values ('3588bba5-d8da-4a36-ad79-b487e71970bb', -- Track tools blog
        'f0b82471-8666-405b-b9d1-26b3ce510b54'); -- Slash

update blog
set number_of_likes = 2
where id = '3588bba5-d8da-4a36-ad79-b487e71970bb'; -- Track tools blog

insert into blog (id, title, content, thumbnail_image_url, create_date, number_of_likes, category, user_id)
values ('db27b313-4e0b-4ee5-84a4-2534bdf59bab',
        'Electric SUV with Ferrari look at a budget price',
        'In March 2024, the Chinese electronics giant and cell phone manufacturer Xiaomi presented its first ever car, the SU7. The electric sedan is selling well on the Chinese market and the Ultra version has also scored points on the Nürburgring Nordschleife. But Xiaomi wants to do more - especially sell more cars - and, as already announced, is expanding its portfolio to include an SUV. In China, the Ministry of Industry and Information Technology (MIIT) has published the first data and images of the YU7 electric SUV. Xiaomi itself released the first official images immediately after the authorities leaked the product. The YU7 is powered by two electric motors - one per axle. The front motor produces 220 kW, while a 288 kW electric motor pushes the rear axle. The total output is given as 508 kW - in old currency that corresponds to around 690 hp. This means that the all-wheel drive vehicle, which weighs around 2.4 tons, should accelerate to up to 253 km/h. Its motors draw energy from an NMC battery from CATL. No capacity has yet been announced. However, the MIIT has published initial range figures based on the CLTC cycle. 750, 760 and 670 kilometers are mentioned. This suggests that the YU7 will be offered in three versions with additional drive and battery options.',
        'https://imgr1.auto-motor-und-sport.de/image-R1100x619-ffffff-C-5abe66c-2164975.jpeg',
        '2025-01-31 10:00:23+00',
        0,
        'ELECTRIC_CARS',
        '29ed49f0-f266-429e-a160-7ce1a34547fe'); -- Jeremy Clarkson user

insert into blog_likes (blog_id, user_id)
values ('db27b313-4e0b-4ee5-84a4-2534bdf59bab', -- Xiaomi SUV blog
        'f0b82471-8666-405b-b9d1-26b3ce510b54'); -- Slash user

update blog
set number_of_likes = 1
where id = 'db27b313-4e0b-4ee5-84a4-2534bdf59bab'; -- Xiaomi SUV blog

insert into blog (id, title, content, thumbnail_image_url, create_date, number_of_likes, category, user_id)
values ('0a4c0286-f6fd-4bd2-8a3f-19c854766d03',
        'Mercedes could bring back the S-Class Coupé',
        'Not long ago, Mercedes cleaned up its coupe series. The two-door variants of the C and E classes were combined to form the CLE, and the Swabians had previously discontinued the S-Class coupe without replacement. But this could soon make a surprise comeback. Two corresponding patent applications were registered with the European Union Intellectual Property Office (EUIPO) a few days ago.',
        'https://imgr1.auto-motor-und-sport.de/image-R1100x619-ffffff-C-aff3897e-2171538.jpeg',
        '2025-02-05 09:17:55+00',
        0,
        'CONCEPTS',
        '29ed49f0-f266-429e-a160-7ce1a34547fe'); -- Jeremy Clarkson user

insert into blog_likes (blog_id, user_id)
values ('0a4c0286-f6fd-4bd2-8a3f-19c854766d03', -- S Class coupe blog
        'e17fffc8-e339-49b6-bc21-9c46b568ed43'); -- Filip Kisic user

insert into blog_likes (blog_id, user_id)
values ('0a4c0286-f6fd-4bd2-8a3f-19c854766d03', -- S Class coupe blog
        'f0b82471-8666-405b-b9d1-26b3ce510b54'); -- Slash user

update blog
set number_of_likes = 2
where id = 'e17fffc8-e339-49b6-bc21-9c46b568ed43'; -- S Class coupe blog

insert into blog (id, title, content, thumbnail_image_url, create_date, number_of_likes, category, user_id)
values ('3dea7bb3-160e-46a3-a1e1-f483a97d5021',
        'These are the best V8 engines in the world',
        'V8 engines enjoy a legendary reputation. But contrary to what many people assume, the idea for the concept did not come from the USA, but from France. The engineer Léon Levavasseur invented the engine in 1902 and installed it in an automobile a year later. The V8 only became known and popular when it was used in mass production in the USA. Cadillac took on the pioneering role here in 1914. The rest is history. More than 100 years later, the V8 is still the backbone of casual US cars. But it also forms the foundation of modern European sports cars and extremely powerful SUVs - often forced-in-air by turbochargers. Or what would Ferrari, McLaren, Aston Martin, Bentley, Porsche, Mercedes-AMG or BMW be without the V8 engine?',
        'https://imgr1.auto-motor-und-sport.de/image-C885x498-ffffff-C-49eee310-2174322.jpeg',
        '2025-02-05 11:11:11+00',
        0,
        'EXOTIC',
        'e17fffc8-e339-49b6-bc21-9c46b568ed43'); -- Filip Kisic user

insert into blog_likes (blog_id, user_id)
values ('3dea7bb3-160e-46a3-a1e1-f483a97d5021', -- V8 blog
        '29ed49f0-f266-429e-a160-7ce1a34547fe'); -- Jeremy Clarkson user

insert into blog_likes (blog_id, user_id)
values ('3dea7bb3-160e-46a3-a1e1-f483a97d5021', -- V8 blog
        'f0b82471-8666-405b-b9d1-26b3ce510b54'); -- Slash user

update blog
set number_of_likes = 3
where id = '3dea7bb3-160e-46a3-a1e1-f483a97d5021'; --V8 blog

insert into blog (id, title, content, thumbnail_image_url, create_date, number_of_likes, category, user_id)
values ('088b22b1-d73e-4952-9e76-db35eb5e93f3',
        'This is how much fuel is saved by the technology that is a nuisance for many',
        'Do you often ride with people whose first action when setting off is to switch off the automatic start-stop system? Or are you one of these people yourself? Then you should perhaps rethink your actions or point out to other drivers that their behavior is costing them money and increasing fuel consumption and thus CO₂ emissions. Because for a small measure such as leaving the start-stop system largely active, it brings a noticeable saving effect - even if it gets annoying from time to time. If the car is stationary, the engine is switched off automatically and switched on again when driving off, without the driver having to press the start button or turn the ignition key. With a vehicle with an automatic transmission, you only have to press the brake pedal as usual for the engine to switch off. If your foot takes your foot off the brake pedal, the car starts again. It is a little more complicated with models with a manual transmission. As a rule, you have to put the car in neutral and/or apply the foot brake for the engine to switch off automatically. In modern cars, this happens more and more often while the car is coasting, for example in vehicles with (mild) hybrid drives. This is called coasting. If you want the engine to start again, all you have to do is press the clutch pedal. And more and more often it starts when the cameras and sensors detect that the vehicle in front is moving again.',
        'https://imgr1.auto-motor-und-sport.de/image-C885x498-ffffff-C-924e5b92-2172381.jpeg',
        '2025-02-03 20:59:29+00',
        0,
        'TECHNOLOGY',
        '29ed49f0-f266-429e-a160-7ce1a34547fe'); -- Jeremy Clarkson user

insert into blog (id, title, content, thumbnail_image_url, create_date, number_of_likes, category, user_id)
values ('cbe429e2-8c6d-4a81-ac33-fb370bd49c80',
        'Rivian patents heated headlights',
        'Rivian has patented heated headlight lenses. Modern LED headlights produce a very white, bright light and are very energy efficient - compared to halogen headlights, they are therefore relatively cold. LED lamps do need a fan to cool their circuit board, but the heat dissipated is not enough to melt snow and ice on the headlight lenses - the halogen lamps, which are now considered outdated, often give off enough heat for that. This is why Rivian has now patented its own high-tech heater for its LED headlights. Rivian submitted its development to the United States Patent and Trademark Office (USPTO) on June 21, 2023 - the office then granted and published the patent on December 26, 2024. In the patent, Rivian engineers describe that the headlights they developed have carbon nanotubes embedded in the outer lens. Carbon nanotubes have good thermal conductivity and at the same time appear to have little impact on the light transmission of the headlight lens.',
        'https://imgr1.auto-motor-und-sport.de/image-C885x498-ffffff-C-c0d9bca9-2087393.jpeg',
        '2025-02-02 14:02:37+00',
        0,
        'TECHNOLOGY',
        '29ed49f0-f266-429e-a160-7ce1a34547fe'); -- Jeremy Clarkson user

insert into blog_likes (blog_id, user_id)
values ('cbe429e2-8c6d-4a81-ac33-fb370bd49c80', -- Rivian headlights blog
        'e17fffc8-e339-49b6-bc21-9c46b568ed43'); -- Filip Kisic user

update blog
set number_of_likes = 1
where id = 'cbe429e2-8c6d-4a81-ac33-fb370bd49c80'; -- Rivian headlights blog

insert into blog (id, title, content, thumbnail_image_url, create_date, number_of_likes, category, user_id)
values ('f2aa9599-785f-4fcc-86a7-1a5e23862379',
        'How to keep the F40 firmly under control',
        'Back then, the silhouette barely wanted to detach itself from the road - so flat, as if it were one with it. And today I get to drive the racing car. Racing car? The comparison is often misused, but is rarely, if ever, accurate. It fits the F40, and that becomes clear when you get in: you climb into a kind of carbon tub, inside which there are two bucket seats, a sober dashboard, steering wheel, pedals, gear lever and fire extinguisher. This asceticism ensures that you are immediately centered in the middle of the action, in the perfectly aligned ergonomics. The three-liter V8 starts without any fuss, and without crying out for attention - an F40 with its huge wing needs attention anyway. The engine simply blares into a slightly elevated, low-humming idle. As is usual with engines with a 180-degree crankshaft, you can''t hear the number of cylinders. But you can hear that, despite numerous moving parts such as eight pistons and connecting rods, four camshafts and 32 valves, the engine is low in inertia: short bursts of gas sound almost as hectic as in a racing car.',
        'https://imgr1.auto-motor-und-sport.de/image-R1100x619-ffffff-C-6bb6cc2c-2174914.jpeg',
        '2025-02-04 16:42:12+00',
        0,
        'OLDTIMER',
        'e17fffc8-e339-49b6-bc21-9c46b568ed43'); -- Filip Kisic user

insert into blog (id, title, content, thumbnail_image_url, create_date, number_of_likes, category, user_id)
values ('d74aa9eb-5d2b-4e24-9cf8-4fed165aa239',
        'This is how expensive Fangio''s Silver Arrow is',
        'Two years after the Uhlenhaut Coupé , RM Sotheby''s auctioned a Silver Arrow that Fangio and Moss drove in races at the Mercedes-Benz Museum. The W 196 R with chassis number 9 represents the successful Silver Arrow era of Mercedes-Benz in the mid-1950s and had been part of the Indianapolis Motor Speedway Museum (IMSM) collection since 1965. The museum has decided to sell some cars from the collection in order to increase the foundation''s capital and renovate the exhibition. The Silver Arrow is the most valuable car in the museum collection. RM Sotheby''s had previously estimated the value of the car at "more than 50 million US dollars". Market experts considered this figure to be rather low. Expectations were therefore high. The W 196 R was to be the second most expensive auction car after the Uhlenhaut Coupé auctioned in 2022. In the end, it was close: the bids quickly climbed from 20 to 30 million. When the 40 million was reached, things got tough. Only two bidders remain. At this sum, the air gets thin: only a handful of bidders were on the phone and in the room. The number of collectors who buy such cars is very small. The hammer fell at 46 million euros. Including the premium, the sales price is 51.155 million euros, the equivalent of 53.917 million dollars. The two most expensive classic cars are Mercedes.',
        'https://imgr1.auto-motor-und-sport.de/image-C885x498-ffffff-C-64fc3d28-2175930.jpeg',
        '2025-02-03 18:06:24+00',
        0,
        'OLDTIMER',
        '29ed49f0-f266-429e-a160-7ce1a34547fe'); -- Jeremy Clarkson user

insert into blog_likes (blog_id, user_id)
values ('d74aa9eb-5d2b-4e24-9cf8-4fed165aa239', -- Silver arrow blog
        'e17fffc8-e339-49b6-bc21-9c46b568ed43'); -- Filip Kisic user

insert into blog_likes (blog_id, user_id)
values ('d74aa9eb-5d2b-4e24-9cf8-4fed165aa239', -- Silver arrow blog
        'f0b82471-8666-405b-b9d1-26b3ce510b54'); -- Slash user

update blog
set number_of_likes = 2
where id = 'd74aa9eb-5d2b-4e24-9cf8-4fed165aa239';