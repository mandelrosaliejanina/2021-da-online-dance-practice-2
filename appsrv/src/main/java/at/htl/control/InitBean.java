package at.htl.control;

import at.htl.entity.*;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Paths;

@ApplicationScoped
public class InitBean {

    @Inject
    LevelRepository levelRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    CourseRepository courseRepository;


    @Inject
    FileRepository fileRepository;

    @Inject
    UsageRepository usageRepository;

    @Inject
    AccessTokenRepository accessTokenRepository;

    void onStart(@Observes StartupEvent event) {
        initDb();
    }

    @Transactional
    void initDb() {
        String resourcesPath = Paths
                .get("")
                .toAbsolutePath() +
                "/src/main/resources/META-INF/resources/";

        File mediafilesFolder = new File(resourcesPath + "mediafiles");
        File videoFolder = new File(resourcesPath + "mediafiles/video");
        File audioFolder = new File(resourcesPath + "mediafiles/audio");

        mediafilesFolder.mkdir();
        videoFolder.mkdir();
        audioFolder.mkdir();

        //Level
        Level grundkurs = new Level("GRUNDKURS", "GRUNDKURS");
        levelRepository.persist(grundkurs);
        Level bronze = new Level("BRONZE", "BRONZE");
        levelRepository.persist(bronze);
        Level silber = new Level("SILBER", "SILBER");
        levelRepository.persist(silber);
        Level gold = new Level("GOLD", "GOLD");
        levelRepository.persist(gold);
        Level goldstar = new Level("GOLDSTAR", "GOLDSTAR");
        levelRepository.persist(goldstar);
        Level topclass = new Level("TOPCLASS", "TOPCLASS");
        levelRepository.persist(topclass);

        //User
        User kelly = new User("KellyTran03", "Kelly", "Tran", "12324", "TEACHER");
        userRepository.persist(kelly);
        User rosalie = new User("RosalieMandel14", "Rosalie", "Mandel ", "456", "TEACHER");
        userRepository.persist(rosalie);
        User sandy = new User("SandyTang24", "Sandy", "Tang ", "sandypw", "TEACHER");
        userRepository.persist(sandy);
        User student = new User("StudentAccount", "student", "student", "passwordStudent", "STUDENT");
        userRepository.persist(student);
        User teacher = new User("TeacherAccount", "teacher", "teacher ", "passwordTeacher", "TEACHER");
        userRepository.persist(teacher);
        User victoriaStudent = new User("VictoriaStudentAccount", "Victoria", "Lauda ", "danceismypassion", "STUDENT");
        userRepository.persist(victoriaStudent);
        User victoriaTeacher = new User("VictoriaTeacherAccount", "Victoria", "Lauda ", "teachingdanceismypassion", "TEACHER");
        userRepository.persist(victoriaTeacher);

        //Course Grundkurs Level

        Course chachacha1 = new Course("Cha Cha Cha"," ",grundkurs);
        courseRepository.persist(chachacha1);
        Course jive1 = new Course("Jive"," ",grundkurs);
        courseRepository.persist(jive1);
        Course langsamerWalzer1 = new Course("Langsamer Walzer"," ",grundkurs);
        courseRepository.persist(langsamerWalzer1);
        Course pasoDoble1 = new Course("Paso Doble"," ",grundkurs);
        courseRepository.persist(pasoDoble1);
        Course quickStep1 = new Course("Quick Step"," ",grundkurs);
        courseRepository.persist(quickStep1);
        Course rumba1 = new Course("Rumba"," ",grundkurs);
        courseRepository.persist(rumba1);

        // Courses Broze Level
        Course chachacha2 = new Course("Cha Cha Cha"," ",bronze);
        courseRepository.persist(chachacha2);
        Course jive2 = new Course("Jive"," ",bronze);
        courseRepository.persist(jive2);
        Course langsamerWalzer2 = new Course("Langsamer Walzer"," ",bronze);
        courseRepository.persist(langsamerWalzer2);
        Course pasoDoble2 = new Course("Paso Doble"," ",bronze);
        courseRepository.persist(pasoDoble2);
        Course quickStep2 = new Course("Quick Step"," ",bronze);
        courseRepository.persist(quickStep2);
        Course rumba2 = new Course("Rumba"," ",bronze);
        courseRepository.persist(rumba2);

        // Course Silver Level
        Course chachacha3 = new Course("Cha Cha Cha"," ",silber);
        courseRepository.persist(chachacha3);
        Course jive3 = new Course("Jive"," ",silber);
        courseRepository.persist(jive3);
        Course langsamerWalzer3 = new Course("Langsamer Walzer"," ",silber);
        courseRepository.persist(langsamerWalzer3);
        Course pasoDoble3 = new Course("Paso Doble"," ",silber);
        courseRepository.persist(pasoDoble3);
        Course quickStep3 = new Course("Quick Step"," ",silber);
        courseRepository.persist(quickStep3);
        Course rumba3 = new Course("Rumba"," ",silber);
        courseRepository.persist(rumba3);


        // Course Gold Level
        Course chachacha4 = new Course("Cha Cha Cha"," ",gold);
        courseRepository.persist(chachacha4);
        Course jive4 = new Course("Jive"," ",gold);
        courseRepository.persist(jive4);
        Course langsamerWalzer4 = new Course("Langsamer Walzer"," ",gold);
        courseRepository.persist(langsamerWalzer4);
        Course pasoDoble4 = new Course("Paso Doble"," ",gold);
        courseRepository.persist(pasoDoble4);
        Course quickStep4 = new Course("Quick Step"," ",gold);
        courseRepository.persist(quickStep4);
        Course rumba4 = new Course("Rumba"," ",gold);
        courseRepository.persist(rumba4);

        // Course Goldstar Level
        Course chachacha5 = new Course("Cha Cha Cha"," ",goldstar);
        courseRepository.persist(chachacha5);
        Course jive5 = new Course("Jive"," ",goldstar);
        courseRepository.persist(jive5);
        Course langsamerWalzer5 = new Course("Langsamer Walzer"," ",goldstar);
        courseRepository.persist(langsamerWalzer5);
        Course pasoDoble5= new Course("Paso Doble"," ",goldstar);
        courseRepository.persist(pasoDoble5);
        Course quickStep5 = new Course("Quick Step"," ",goldstar);
        courseRepository.persist(quickStep5);
        Course rumba5 = new Course("Rumba"," ",goldstar);
        courseRepository.persist(rumba5);

        // Course Goldstar Level
        Course chachacha6 = new Course("Cha Cha Cha"," ",topclass);
        courseRepository.persist(chachacha6);
        Course jive6 = new Course("Jive"," ",topclass);
        courseRepository.persist(jive6);
        Course langsamerWalzer6 = new Course("Langsamer Walzer"," ",topclass);
        courseRepository.persist(langsamerWalzer6);
        Course pasoDoble6= new Course("Paso Doble"," ",topclass);
        courseRepository.persist(pasoDoble6);
        Course quickStep6 = new Course("Quick Step"," ",topclass);
        courseRepository.persist(quickStep6);
        Course rumba6 = new Course("Rumba"," ",topclass);
        courseRepository.persist(rumba6);


        AccessToken accessToken = new AccessToken(chachacha1);
        accessTokenRepository.persist(accessToken);
    }
}
