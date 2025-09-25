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
import java.util.*

@Component
@Profile("dev")
final class DevDataLoader(
    private val userApiFacade: UserApiFacade,
    private val blogRepository: JpaBlogEntityRepository
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        //************************* User models *************************//
        val filipDetails = UserInputDetails("filip@kisic.com", "filipkisic", "Pa$\$w0rd", "Pa$\$w0rd", null)
        val jeremyDetails = UserInputDetails("jeremy@clarkson.com", "jeremyclarkson", "Pa$\$w0rd", "Pa$\$w0rd", null)
        val slashDetails = UserInputDetails("saul@hudson.com", "slash", "Pa$\$w0rd", "Pa$\$w0rd", null)

        //************************* User registration process *************************//
        val filipUser = userApiFacade.signUp(filipDetails)
        val jeremyUser = userApiFacade.signUp(jeremyDetails)
        userApiFacade.signUp(slashDetails)

        //************************* Blog models *************************//
        val mercedesV12Blog = BlogEntity(
            UUID.randomUUID().toString(),
            "Mercedes-Benz Isn't Killing the V12 Anytime Soon",
            "To borrow a famously misquoted line from Mark Twain, reports of the demise of the V-12 engine have been greatly exaggerated. The flagship for combustion-engine smoothness and complexity was starting to be considered a bit of a dinosaur, staring at a weirdly ominous bright spot in the sky, but now it looks like manufacturers are no longer so keen to stop making it. Markus Schäfer, Mercedes-Benz chief technology officer, recently told U.K.-based Autocar that the company intends to keep providing V-12-powered machines well into the 2030s. Speaking at the Munich auto show, Schäfer declined to say whether V-12s would still be available in Europe or North America, but he didn't completely close the door on the idea either. For the European market, new emissions laws set to arrive at the tail end of 2026 will signal a death knell for Mercedes's current V-12. As it stands, the only 12-cylinder model on the market wearing a three-pointed star is the Mercedes-Maybach S680.",
            "https://hips.hearstapps.com/hmg-prod/amv-prod-cad-assets/wp-content/uploads/2018/02/2018-Mercedes-AMG-S65-193.jpg",
            filipUser.id,
            LocalDateTime.of(2025, 9, 19, 13, 49),
            0,
            BlogCategoryEntity.EXOTIC
        )

        val audiGearboxBlog = BlogEntity(
            UUID.randomUUID().toString(),
            "Audi's EV Sports Car Could Have a Virtual Gearbox",
            "Last week, Audi unveiled the sharp-looking Concept C, previewing a new design language with a rectangular, vertically oriented grille and thin LED headlights. But the Concept C doesn't just serve as a vision for the brand's styling; it will actually go into production within the next few years as an indirect electric successor to the TT sports car. Now, in an interview with Top Gear, CEO Gernot Döllner has revealed a bit more of what to expect from Audi's upcoming performance EV. Döllner told Top Gear the concept is about 90 percent similar to the production model, saying, \"During my time at Audi, I will only present concept cars that have a decision to be produced.\" He says the company aims to get the production version ready by 2027, and clarified that it will not carry the TT badge. This technology isn't officially confirmed for the production version of the Concept C, but Döllner said the automaker is developing a virtual \"gearbox\" and that Audi is \"quite open to finding innovative solutions in this area.\" Döllner also hinted at fake engine noises, suggesting that the sound of the soon-to-be-retired five-cylinder engine found in the RS3 could maybe \"come back virtually.\" There are still a few years before we'll be able to drive the production-spec sports car, but we're definitely intrigued.",
            "https://hips.hearstapps.com/hmg-prod/images/a251470-large-68b7179960d03.jpg?crop=1xw:0.889068100358423xh;center,top",
            jeremyUser.id,
            LocalDateTime.of(2025, 9, 19, 14, 7),
            0,
            BlogCategoryEntity.TECHNOLOGY
        )

        val newApolloBlog = BlogEntity(
            UUID.randomUUID().toString(),
            "Another Apollo Supercar is Coming",
            "In another world, the Gumpert Apollo could have sat alongside the Koenigsegg CC and Pagani Zonda lines as the origin points for decades of beloved hypercars to come. Instead, the brand and its one-time record setter faded into irrelevance after a 2013 bankruptcy. A successor company, Apollo, carried on with the oddly named Intensa Emozione in the late 2010s. Now, that Apollo has its successor. This is the Apollo Evo, a limited-run track hypercar meant to celebrate the 20th anniversary of Apollo predecessor Gumpert. It is billed as a follow-up to the Intensa Emozione, although its status as a track car puts it in competition with very different vehicles than the road-focused IE hypercar. Just 10 Evos will be built, making the new car a rarity even in the world of ultra-specialized track cars.",
            "",
            jeremyUser.id,
            LocalDateTime.of(2025, 9, 20, 17, 33),
            0,
            BlogCategoryEntity.HOT_NEWS
        )

        //************************* Blog creation ************************* //
        blogRepository.save(mercedesV12Blog)
        blogRepository.save(audiGearboxBlog)
        blogRepository.save(newApolloBlog)
    }
}