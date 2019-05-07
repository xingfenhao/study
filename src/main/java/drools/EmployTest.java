package drools;


import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.logger.KnowledgeRuntimeLogger;
import org.kie.internal.logger.KnowledgeRuntimeLoggerFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

public class EmployTest {


    public static void main(String[] args) {


        try {

// load up the knowledge base

            KnowledgeBase kbase = readKnowledgeBase();

            StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

            KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");

// go !

            Employ emp = new Employ();

            emp.setEduInfo("master");

            emp.setResume("tech");

            emp.setAnnualExam("good");

            emp.setAwardPunish("award");

            ksession.insert(emp);

            ksession.startProcess("myrules.salary");

            ksession.fireAllRules();

            System.out.println("Basic Salary: " + emp.getBasicSalary());

            System.out.println("Duty Salary: " + emp.getDutySalary());

            System.out.println("Bonus : " + emp.getBonus());

            System.out.println("rate : " + emp.getPercent());

            System.out.printf("Total Salary: %.0f" , emp.getTotalSalary());

            logger.close();

        } catch (Throwable t) {

            t.printStackTrace();

        }


    }


    private static KnowledgeBase readKnowledgeBase() throws Exception {

        KnowledgeBuilder kbuilder =

                KnowledgeBuilderFactory.newKnowledgeBuilder();

        kbuilder.add(ResourceFactory.newClassPathResource("EduInfoRule.drl"), ResourceType.DRL);

        kbuilder.add(ResourceFactory.newClassPathResource("ResumeRule.drl"), ResourceType.DRL);

        kbuilder.add(ResourceFactory.newClassPathResource("BonusRule.drl"), ResourceType.DRL);

        kbuilder.add(ResourceFactory.newClassPathResource("AwardPunish.drl"), ResourceType.DRL);

        kbuilder.add(ResourceFactory.newClassPathResource("TotalRule.drl"), ResourceType.DRL);

        kbuilder.add(ResourceFactory.newClassPathResource("salary.rf"), ResourceType.DRF);

        KnowledgeBuilderErrors errors = kbuilder.getErrors();

        if (errors.size() > 0) {

            for (KnowledgeBuilderError error: errors) {

                System.err.println(error);

            }

            throw new IllegalArgumentException("Could not parse knowledge.");

        }

        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();

        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

        return kbase;

    }


}
