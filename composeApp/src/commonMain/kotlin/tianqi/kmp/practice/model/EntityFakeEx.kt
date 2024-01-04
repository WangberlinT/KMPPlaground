package tianqi.kmp.practice.model

fun RocketLaunch.Companion.fakeLaunches() = listOf(
    RocketLaunch(
        flightNumber = 1,
        missionName = "FalconSat",
        launchDateUTC = "2006-03-24T22:30:00.000Z",
        details = "Engine failure at 33 seconds and loss of vehicle",
        launchSuccess = false,
        links = Links.fakeLinks(),
    ),
    RocketLaunch(
        flightNumber = 1,
        missionName = "FalconSat",
        launchDateUTC = "2006-03-24T22:30:00.000Z",
        details = "Engine failure at 33 seconds and loss of vehicle",
        launchSuccess = false,
        links = Links.fakeLinks().copy(
            patch = Patch(
                small = "https://images2.fake.com/3c/0e/T8iJcSN3_o.png",
                large = "https://images2.fake.com/40/e3/GypSkayF_o.png"
            )
        ),
    )
)

fun Links.Companion.fakeLinks() = Links(
    patch = Patch.fakePatch(),
    article = "https://www.space.com/2196-spacex-inaugural-falcon-1-rocket-lost-launch.html"
)

fun Patch.Companion.fakePatch() = Patch(
    small = "https://images2.imgbox.com/3c/0e/T8iJcSN3_o.png",
    large = "https://images2.imgbox.com/40/e3/GypSkayF_o.png"
)