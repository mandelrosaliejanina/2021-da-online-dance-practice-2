package at.htl.control;

import at.htl.entity.*;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class InitBean {

    @Inject
    LevelRepository levelRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    CourseRepository courseRepository;

    @Inject
    BookingRepository bookingRepository;

    @Inject
    FileRepository fileRepository;

    @Inject
    UsageRepository usageRepository;

    void onStart(@Observes StartupEvent event) {
        initDb();
    }

    @Transactional
    void initDb() {

        //Level
        Level grundkurs = new Level("GRUNDKURS","GRUNDKURS");
        levelRepository.persist(grundkurs);
        Level bronze = new Level("BRONZE","BRONZE");
        levelRepository.persist(bronze);
        Level silber = new Level("SILBER","SILBER");
        levelRepository.persist(silber);
        Level gold = new Level("GOLD","GOLD");
        levelRepository.persist(gold);
        Level goldstar = new Level("GOLDSTAR","GOLDSTAR");
        levelRepository.persist(goldstar);
        Level topclass = new Level("TOPCLASS","TOPCLASS");
        levelRepository.persist(topclass);

        //User
        User kelly = new User("KellyTran03","Kelly", "Tran ");
        userRepository.persist(kelly);
        User rosalie = new User("RosalieMandel14","Rosalie", "Mandel ");
        userRepository.persist(rosalie);
        User sandy = new User("SandyTang24","Sandy", "Tang ");
        userRepository.persist(sandy);
        User anton = new User("Anton123","Anton", "Traxler");
        userRepository.persist(anton);
        User lisa = new User("Lisa124","Lisa", "Müller ");
        userRepository.persist(lisa);
        User jonas = new User("JonasT123","Jonas", "Berg ");
        userRepository.persist(jonas);

        //Course
        Course discofox = new Course ("Grundschritte & Figuren","Für alle Gelengheiten", grundkurs);
        courseRepository.persist(discofox);
        Course westcoastswing = new Course ("Der internationale Trend ","Sehr cool!", grundkurs);
        courseRepository.persist(westcoastswing);
        Course tanzclubs = new Course ("Plus Ballroom & Slowfox","In Ried, Regau ubd Wels", grundkurs);
        courseRepository.persist(tanzclubs);
        Course pirvatstunden = new Course ("Individuell buchbar", "Termin vereinbaren!", grundkurs);
        courseRepository.persist(pirvatstunden);


        //Booking
        Booking booking01 = new Booking(kelly,discofox);
        bookingRepository.persist(booking01);
        Booking booking02 = new Booking(rosalie,westcoastswing);
        bookingRepository.persist(booking02);
        Booking booking03 = new Booking(sandy,tanzclubs);
        bookingRepository.persist(booking03);
        Booking booking04 = new Booking(anton,pirvatstunden);
        bookingRepository.persist(booking04);


        //File
        //audio
        D_File senorita = new D_File("01_CHA-Senorita(original).wav", "mediafiles/audio", ContentType.valueOf("AUDIO"));
        fileRepository.persist(senorita);
        D_File ice = new D_File("03_LikeIceinthesunshine.mp3", "mediafiles/audio", ContentType.valueOf("AUDIO"));
        fileRepository.persist(ice);
        D_File coupe = new D_File("04_CoupedeVille.mp3", "mediafiles/audio", ContentType.valueOf("AUDIO"));
        fileRepository.persist(coupe);
        D_File california = new D_File("05_HotelCalifornia-DJRoy-(LatinGoodVibration2)-AO-WM,EN.mp3", "mediafiles/audio", ContentType.valueOf("AUDIO"));
        fileRepository.persist(california);
        D_File wonder = new D_File("06_lwonderwhy-03-slow-waltz.wav", "mediafiles/audio", ContentType.valueOf("AUDIO"));
        fileRepository.persist(wonder);
        D_File promises = new D_File("07-Promises-TanzorchesterKlausHallen-(ChartbreakerVol. 21)-ZL- PP,EN.mp3", "mediafiles/audio", ContentType.valueOf("AUDIO"));
        fileRepository.persist(promises);
        D_File fading = new D_File("08-Fading-TanzorchesterKlausHallen-(ChartbreakerVol.21)-ZL-PP,EN.mp3", "mediafiles/audio", ContentType.valueOf("AUDIO"));
        fileRepository.persist(california);
        D_File bachataDeja = new D_File("09Bachata-Dejavu.mp3", "mediafiles/audio", ContentType.valueOf("AUDIO"));
        fileRepository.persist(bachataDeja);
        D_File sal = new D_File("10_SAL-Feliceslos4(SalsaVersio.wav", "mediafiles/audio", ContentType.valueOf("AUDIO"));
        fileRepository.persist(sal);
        D_File havana = new D_File("11-31-Havana-DJBernat-(LatinMusic14)-Casaphon-PP,EN.mp3", "mediafiles/audio", ContentType.valueOf("AUDIO"));
        fileRepository.persist(havana);
        D_File mama = new D_File("12-51-Mama-DJRicoLatino-(LatinMusic14)-Casaphon-PP,EN.mp3", "mediafiles/audio", ContentType.valueOf("AUDIO"));
        fileRepository.persist(mama);
        D_File sweet = new D_File("13SweetButPsycho-TanzorchesterKlausHallen-(ChartbreakerVol.21)-ZL-PP,EN.mp3", "mediafiles/audio", ContentType.valueOf("AUDIO"));
        fileRepository.persist(sweet);
        D_File tango = new D_File("14_TangoPorUnaCabeza-instrumentalviolincover-stringquartet.mp3", "mediafiles/audio", ContentType.valueOf("AUDIO"));
        fileRepository.persist(tango);
        D_File lieblingmensch = new D_File("15-29-Lieblingsmensch-Lila-(BallroomNights8)-Casaphon-PP,DE.mp3", "mediafiles/audio", ContentType.valueOf("AUDIO"));
        fileRepository.persist(lieblingmensch);
        D_File roses = new D_File("16_WW-RosesFromTheSouth.mp3", "mediafiles/audio", ContentType.valueOf("AUDIO"));
        fileRepository.persist(roses);
        D_File paso = new D_File("17-PASO-Manolita60.mp3", "mediafiles/audio", ContentType.valueOf("AUDIO"));
        fileRepository.persist(paso);
        D_File aldo = new D_File("18-AldoCapioccioni-05-MyWay-SW28.wav", "mediafiles/audio", ContentType.valueOf("AUDIO"));
        fileRepository.persist(aldo);
        D_File tuesday = new D_File("19-Tuesday(feat.DanelleSandoval).mp3", "mediafiles/audio", ContentType.valueOf("AUDIO"));
        fileRepository.persist(tuesday);
        D_File dancelife = new D_File("20-IStillHaven'tFound(shortversion)-DancelifeStudioOrchestra-(Bring16smilestoyoufeet)-Dancelife-LP,SP.mp3", "mediafiles/audio", ContentType.valueOf("AUDIO"));
        fileRepository.persist(dancelife);
        D_File bailando = new D_File("21_SAL-Bailando(feat.DescemerBu.mp3", "mediafiles/audio", ContentType.valueOf("AUDIO"));
        fileRepository.persist(bailando);
        D_File ready = new D_File("22_I'mReady.mp3","mediafiles/audio", ContentType.valueOf("AUDIO"));
        fileRepository.persist(ready);
        D_File df = new D_File("23_DF-Warumhastdunichtneinges.mp3", "mediafiles/audio", ContentType.valueOf("AUDIO"));
        fileRepository.persist(df);
        D_File ex = new D_File("24-50-Ex'sAndOh's-Swingpop!-(BallroomNights8)-Casaphon-SW,EN.mp3", "mediafiles/audio", ContentType.valueOf("AUDIO"));
        fileRepository.persist(ex);
        D_File hold = new D_File("25-29-HoldMyGirl-TanzorchesterKlausHallen-(ChartbreakerVol.21)-ZL-PP,EN.mp3", "mediafiles/audio", ContentType.valueOf("AUDIO"));
        fileRepository.persist(hold);
        D_File sed = new D_File("26_SeddeTi...DustinRichie...(bachatanew2019)(audio).mp3", "mediafiles/audio", ContentType.valueOf("AUDIO"));
        fileRepository.persist(sed);
        D_File bang = new D_File("27-BangBang,Part2-Genio&Pierrots-(LatinMegaHits6)-CM-IT,PP.mp3", "mediafiles/audio", ContentType.valueOf("AUDIO"));
        fileRepository.persist(bang);

        D_File bachata = new D_File("Bachata-OnlineTanzkurs.mp4", "mediafiles/video/html/media", ContentType.valueOf("VIDEO"));
        fileRepository.persist(bachata);
        D_File hiphop = new D_File("HipHopBartSimpson.mp4", "mediafiles/video", ContentType.valueOf("VIDEO"));
        fileRepository.persist(hiphop);
        D_File file02 = new D_File("HipHopBounce.mp4", "mediafiles/video", ContentType.valueOf("VIDEO"));
        fileRepository.persist(file02);
        D_File file04 = new D_File("SalsaChrisFigurdesMonats.mp4", "mediafiles/video", ContentType.valueOf("VIDEO"));
        fileRepository.persist(file04);
        D_File file05 = new D_File("WienerWalzer.mp4", "mediafiles/video", ContentType.valueOf("VIDEO"));
        fileRepository.persist(file05);
        D_File file06 = new D_File("output.mp4", "mediafiles/video", ContentType.valueOf("VIDEO"));
        fileRepository.persist(file06);

        //Usage
        Usage usage02 = new Usage(westcoastswing,file02);
        usageRepository.persist(usage02);
        Usage usage04 = new Usage(pirvatstunden,file04);
        usageRepository.persist(usage04);

    }

}
