package de.comsystoreply.gearbox.application.config

import de.comsystoreply.gearbox.application.blog.adapter.persistance.JpaBlogEntityRepository
import de.comsystoreply.gearbox.application.blog.model.BlogCategoryEntity
import de.comsystoreply.gearbox.application.blog.model.BlogEntity
import de.comsystoreply.gearbox.domain.user.port.api.UserApiFacade
import de.comsystoreply.gearbox.domain.user.port.api.UserInputDetails
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.UUID

@Component
@Profile("placeholder") // To run this, put "qa" as profile. Run it just once to fill the database.
final class QaDataLoader(
    private val userApiFacade: UserApiFacade,
    private val blogRepository: JpaBlogEntityRepository,
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        //************************* User models *************************//
        val filipDetails = UserInputDetails("filip@kisic.com", "filipkisic", "Pa$\$w0rd", "Pa$\$w0rd", null)
        val jeremyDetails = UserInputDetails("jeremy@clarkson.com", "jeremyclarkson", "Pa$\$w0rd", "Pa$\$w0rd", null)
        val slashDetails = UserInputDetails("saul@hudson.com", "slash", "Pa$\$w0rd", "Pa$\$w0rd", null)

        //************************* User registration process *************************//
        val filipUser = userApiFacade.signUp(filipDetails)
        val jeremyUser = userApiFacade.signUp(jeremyDetails)
        val slashUser = userApiFacade.signUp(slashDetails)

        //************************* Blog models *************************//
        val futureOfElectricVehiclesBlog = BlogEntity(
            UUID.randomUUID().toString(),
            "The Future of Electric Vehicles",
            "The automotive industry is rapidly shifting towards electric vehicles (EVs), with advancements in battery technology, charging infrastructure, and autonomous driving. These innovations are not just transforming how we drive, but also how we think about the environment and sustainability.",
            "https://www.netcarshow.com/MG-ZS_Hybrid-2025-1280-8f1072f01e9c7f8cfaeab36bc03ccb8fa4.jpg",
            filipUser.id,
            LocalDateTime.of(2025, 8, 31, 10, 0),
            0,
            BlogCategoryEntity.TECHNOLOGY,
        )

        val hydrogenPoweredVehicleBlog = BlogEntity(
            UUID.randomUUID().toString(),
            "Breaking: Major Automaker Announces New Hydrogen-Powered Vehicle",
            "In a surprising move, a leading automaker has announced the launch of a new hydrogen-powered vehicle, aiming to rival the growing electric vehicle market. This marks a significant development in the pursuit of alternative fuels and could reshape the future of automotive energy.",
            "https://i.ytimg.com/vi/Ppuvvr6rcb0/maxresdefault.jpg",
            jeremyUser.id,
            LocalDateTime.of(2025, 8, 30, 14, 30),
            0,
            BlogCategoryEntity.HOT_NEWS,
        )

        val timelessCharmClassicCarsBlog = BlogEntity(
            UUID.randomUUID().toString(),
            "The Timeless Charm of Classic Cars",
            "Classic cars continue to captivate enthusiasts with their timeless design, craftsmanship, and the nostalgia they evoke. From the curves of a vintage Ferrari to the elegance of a 1960s Mustang, these vehicles are more than just cars; they are pieces of art on wheels.",
            "https://www.netcarshow.com/Mercedes-Benz-SL-Class-1971-1280-1a0aa47d17995bf9c4b4182a00672e8544.jpg",
            slashUser.id,
            LocalDateTime.of(2025, 8, 29, 9, 15),
            0,
            BlogCategoryEntity.OLDTIMER,
        )

        val exoticSupercarsBlog = BlogEntity(
            UUID.randomUUID().toString(),
            "Exploring the World of Exotic Supercars",
            "Exotic supercars represent the pinnacle of automotive engineering, with their powerful engines, sleek designs, and unparalleled performance. From Lamborghini to Bugatti, these cars are designed to thrill, turning heads wherever they go.",
            "https://d1i1eo6qmdfmdv.cloudfront.net/upload/site/pages/newslider_otherpages/slider/EXR_FERRARI_ALL_SUPERCARS_SLIDERS_0004_1.jpg",
            filipUser.id,
            LocalDateTime.of(2025, 8, 28, 11, 45),
            0,
            BlogCategoryEntity.EXOTIC,
        )

        val selfDrivingCarBlog = BlogEntity(
            UUID.randomUUID().toString(),
            "Automaker Unveils Revolutionary Self-Driving Car",
            "A major automaker has just unveiled its latest self-driving car, equipped with cutting-edge AI and sensor technology. This marks a significant leap forward in autonomous driving, promising safer and more efficient transportation in the near future.",
            "https://dda.ndus.edu/ddreview/wp-content/uploads/sites/18/2021/10/selfDriving.png",
            jeremyUser.id,
            LocalDateTime.of(2025, 8, 27, 8, 30),
            0,
            BlogCategoryEntity.TECHNOLOGY,
        )

        val batteryTechBlog = BlogEntity(
            UUID.randomUUID().toString(),
            "Groundbreaking Battery Tech Extends EV Range",
            "A new breakthrough in battery technology is set to extend the range of electric vehicles by up to 30%. This innovation could be a game-changer for the EV market, addressing one of the main concerns of potential buyers.",
            "https://images.tayna.com/prod-images/1200/Powerline/065-powerline-45-435.jpg",
            filipUser.id,
            LocalDateTime.of(2024, 8, 2, 15, 20),
            0,
            BlogCategoryEntity.TECHNOLOGY,
        )

        val evRecallBlog = BlogEntity(
            UUID.randomUUID().toString(),
            "Breaking News: Major Recall on Electric Vehicles",
            "Several major automakers have issued a recall on a range of electric vehicles due to concerns over potential battery overheating. This recall affects thousands of vehicles worldwide and has raised questions about the safety of current EV technology.",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRnhrk7PWAtz_HrY7VLA9_08DH7NsaN2xW6xA&s",
            jeremyUser.id,
            LocalDateTime.of(2024, 7, 15, 13, 10),
            0,
            BlogCategoryEntity.HOT_NEWS,
        )

        val emissionStandardsBlog = BlogEntity(
            UUID.randomUUID().toString(),
            "New Emission Standards Shake Up the Auto Industry",
            "Newly announced emission standards are set to have a massive impact on the automotive industry, pushing manufacturers to accelerate their shift towards cleaner energy vehicles. This development is expected to have wide-ranging effects on vehicle design and production.",
            "https://static.euronews.com/articles/stories/06/80/92/08/1200x675_cmsv2_9c54e623-2817-505a-be18-a079737aa894-6809208.jpg",
            jeremyUser.id,
            LocalDateTime.of(2024, 6, 24, 17, 45),
            0,
            BlogCategoryEntity.HOT_NEWS,
        )

        val bestV8EnginesBlog = BlogEntity(
            UUID.randomUUID().toString(),
            "These are the best V8 engines in the world",
            "V8 engines enjoy a legendary reputation. But contrary to what many people assume, the idea for the concept did not come from the USA, but from France. The engineer Léon Levavasseur invented the engine in 1902 and installed it in an automobile a year later. The V8 only became known and popular when it was used in mass production in the USA. Cadillac took on the pioneering role here in 1914. The rest is history. More than 100 years later, the V8 is still the backbone of casual US cars. But it also forms the foundation of modern European sports cars and extremely powerful SUVs - often forced-in-air by turbochargers. Or what would Ferrari, McLaren, Aston Martin, Bentley, Porsche, Mercedes-AMG or BMW be without the V8 engine?",
            "https://imgr1.auto-motor-und-sport.de/image-C885x498-ffffff-C-49eee310-2174322.jpeg",
            filipUser.id,
            LocalDateTime.of(2025, 2, 5, 11, 11, 11),
            0,
            BlogCategoryEntity.EXOTIC,
        )

        val startStopTechnologyBlog = BlogEntity(
            UUID.randomUUID().toString(),
            "This is how much fuel is saved by the technology that is a nuisance for many",
            "Do you often ride with people whose first action when setting off is to switch off the automatic start-stop system? Or are you one of these people yourself? Then you should perhaps rethink your actions or point out to other drivers that their behavior is costing them money and increasing fuel consumption and thus CO₂ emissions. Because for a small measure such as leaving the start-stop system largely active, it brings a noticeable saving effect - even if it gets annoying from time to time. If the car is stationary, the engine is switched off automatically and switched on again when driving off, without the driver having to press the start button or turn the ignition key. With a vehicle with an automatic transmission, you only have to press the brake pedal as usual for the engine to switch off. If your foot takes your foot off the brake pedal, the car starts again. It is a little more complicated with models with a manual transmission. As a rule, you have to put the car in neutral and/or apply the foot brake for the engine to switch off automatically. In modern cars, this happens more and more often while the car is coasting, for example in vehicles with (mild) hybrid drives. This is called coasting. If you want the engine to start again, all you have to do is press the clutch pedal. And more and more often it starts when the cameras and sensors detect that the vehicle in front is moving again.",
            "https://imgr1.auto-motor-und-sport.de/image-C885x498-ffffff-C-924e5b92-2172381.jpeg",
            jeremyUser.id,
            LocalDateTime.of(2025, 2, 3, 20, 59, 29),
            0,
            BlogCategoryEntity.TECHNOLOGY,
        )

        val rivianHeatedHeadlightsBlog = BlogEntity(
            UUID.randomUUID().toString(),
            "Rivian patents heated headlights",
            "Rivian has patented heated headlight lenses. Modern LED headlights produce a very white, bright light and are very energy efficient - compared to halogen headlights, they are therefore relatively cold. LED lamps do need a fan to cool their circuit board, but the heat dissipated is not enough to melt snow and ice on the headlight lenses - the halogen lamps, which are now considered outdated, often give off enough heat for that. This is why Rivian has now patented its own high-tech heater for its LED headlights. Rivian submitted its development to the United States Patent and Trademark Office (USPTO) on June 21, 2023 - the office then granted and published the patent on December 26, 2024. In the patent, Rivian engineers describe that the headlights they developed have carbon nanotubes embedded in the outer lens. Carbon nanotubes have good thermal conductivity and at the same time appear to have little impact on the light transmission of the headlight lens.",
            "https://imgr1.auto-motor-und-sport.de/image-C885x498-ffffff-C-c0d9bca9-2087393.jpeg",
            filipUser.id,
            LocalDateTime.of(2025, 2, 2, 14, 2, 37),
            0,
            BlogCategoryEntity.TECHNOLOGY,
        )

        val f40ControlBlog = BlogEntity(
            UUID.randomUUID().toString(),
            "How to keep the F40 firmly under control",
            "Back then, the silhouette barely wanted to detach itself from the road - so flat, as if it were one with it. And today I get to drive the racing car. Racing car? The comparison is often misused, but is rarely, if ever, accurate. It fits the F40, and that becomes clear when you get in: you climb into a kind of carbon tub, inside which there are two bucket seats, a sober dashboard, steering wheel, pedals, gear lever and fire extinguisher. This asceticism ensures that you are immediately centered in the middle of the action, in the perfectly aligned ergonomics. The three-liter V8 starts without any fuss, and without crying out for attention - an F40 with its huge wing needs attention anyway. The engine simply blares into a slightly elevated, low-humming idle. As is usual with engines with a 180-degree crankshaft, you can't hear the number of cylinders. But you can hear that, despite numerous moving parts such as eight pistons and connecting rods, four camshafts and 32 valves, the engine is low in inertia: short bursts of gas sound almost as hectic as in a racing car.",
            "https://imgr1.auto-motor-und-sport.de/image-R1100x619-ffffff-C-6bb6cc2c-2174914.jpeg",
            slashUser.id,
            LocalDateTime.of(2025, 2, 4, 16, 42, 12),
            0,
            BlogCategoryEntity.OLDTIMER,
        )

        val fangioSilverArrowBlog = BlogEntity(
            UUID.randomUUID().toString(),
            "This is how expensive Fangio's Silver Arrow is",
            "Two years after the Uhlenhaut Coupé , RM Sotheby's auctioned a Silver Arrow that Fangio and Moss drove in races at the Mercedes-Benz Museum. The W 196 R with chassis number 9 represents the successful Silver Arrow era of Mercedes-Benz in the mid-1950s and had been part of the Indianapolis Motor Speedway Museum (IMSM) collection since 1965. The museum has decided to sell some cars from the collection in order to increase the foundation's capital and renovate the exhibition. The Silver Arrow is the most valuable car in the museum collection. RM Sotheby's had previously estimated the value of the car at \"more than 50 million US dollars\". Market experts considered this figure to be rather low. Expectations were therefore high. The W 196 R was to be the second most expensive auction car after the Uhlenhaut Coupé auctioned in 2022. In the end, it was close: the bids quickly climbed from 20 to 30 million. When the 40 million was reached, things got tough. Only two bidders remain. At this sum, the air gets thin: only a handful of bidders were on the phone and in the room. The number of collectors who buy such cars is very small. The hammer fell at 46 million euros. Including the premium, the sales price is 51.155 million euros, the equivalent of 53.917 million dollars. The two most expensive classic cars are Mercedes.",
            "https://imgr1.auto-motor-und-sport.de/image-C885x498-ffffff-C-64fc3d28-2175930.jpeg",
            slashUser.id,
            LocalDateTime.of(2025, 2, 3, 18, 6, 24),
            0,
            BlogCategoryEntity.OLDTIMER,
        )

        blogRepository.save(futureOfElectricVehiclesBlog)
        blogRepository.save(hydrogenPoweredVehicleBlog)
        blogRepository.save(timelessCharmClassicCarsBlog)
        blogRepository.save(exoticSupercarsBlog)
        blogRepository.save(selfDrivingCarBlog)
        blogRepository.save(batteryTechBlog)
        blogRepository.save(evRecallBlog)
        blogRepository.save(emissionStandardsBlog)
        blogRepository.save(bestV8EnginesBlog)
        blogRepository.save(startStopTechnologyBlog)
        blogRepository.save(rivianHeatedHeadlightsBlog)
        blogRepository.save(f40ControlBlog)
        blogRepository.save(fangioSilverArrowBlog)
    }
}