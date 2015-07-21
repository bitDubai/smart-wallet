package unit.com.bitdubai.fermat_dmp_plugin.layer.middleware.wallet_factory.developer.bitdubai.version_1.structure.WalletFactoryMiddlewareProjectResource;

import com.bitdubai.fermat_api.layer.all_definition.enums.Languages;
import com.bitdubai.fermat_api.layer.dmp_middleware.wallet_factory.enums.FactoryProjectState;
import com.bitdubai.fermat_api.layer.dmp_middleware.wallet_factory.interfaces.WalletFactoryProjectLanguage;
import com.bitdubai.fermat_api.layer.dmp_middleware.wallet_factory.interfaces.WalletFactoryProjectResource;
import com.bitdubai.fermat_api.layer.dmp_middleware.wallet_factory.interfaces.WalletFactoryProjectSkin;
import com.bitdubai.fermat_dmp_plugin.layer.middleware.wallet_factory.developer.bitdubai.version_1.structure.WalletFactoryMiddlewareProjectLanguage;
import com.bitdubai.fermat_dmp_plugin.layer.middleware.wallet_factory.developer.bitdubai.version_1.structure.WalletFactoryMiddlewareProjectProposal;
import com.bitdubai.fermat_dmp_plugin.layer.middleware.wallet_factory.developer.bitdubai.version_1.structure.WalletFactoryMiddlewareProjectSkin;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import ae.com.sun.xml.bind.v2.model.annotation.RuntimeInlineAnnotationReader;
import ae.com.sun.xml.bind.v2.model.annotation.XmlSchemaMine;
import ae.javax.xml.bind.JAXBContext;
import ae.javax.xml.bind.Marshaller;
import ae.javax.xml.bind.Unmarshaller;

public class ProbandoTest extends TestCase {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testSkinsMarshalUnmarshal() throws Exception {
        try {
            File file = new File("/home/lnacosta/Desktop/skins.xml");

            RuntimeInlineAnnotationReader.cachePackageAnnotation(WalletFactoryMiddlewareProjectSkin.class.getPackage(), new XmlSchemaMine(""));
            JAXBContext jaxbContext = JAXBContext.newInstance(WalletFactoryMiddlewareProjectSkin.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            WalletFactoryMiddlewareProjectSkin que = (WalletFactoryMiddlewareProjectSkin) jaxbUnmarshaller.unmarshal(file);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

            System.out.println(que.getName() + que.getHash());
            jaxbMarshaller.marshal(que, System.out);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProposalMarshalUnmarshal() throws Exception {
        try {
            File file = new File("/home/lnacosta/Desktop/skins.xml");

            RuntimeInlineAnnotationReader.cachePackageAnnotation(WalletFactoryMiddlewareProjectSkin.class.getPackage(), new XmlSchemaMine(""));

            JAXBContext jaxbContext = JAXBContext.newInstance(WalletFactoryMiddlewareProjectSkin.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            WalletFactoryMiddlewareProjectSkin que = (WalletFactoryMiddlewareProjectSkin) jaxbUnmarshaller.unmarshal(file);

            List<WalletFactoryProjectSkin> skins = new ArrayList<>();
            skins.add(que);
            skins.add(que);

            WalletFactoryMiddlewareProjectProposal proposal = new WalletFactoryMiddlewareProjectProposal("soyunapropuesta", FactoryProjectState.DISMISSED, skins, null);

            RuntimeInlineAnnotationReader.cachePackageAnnotation(WalletFactoryMiddlewareProjectProposal.class.getPackage(), new XmlSchemaMine(""));
            jaxbContext = JAXBContext.newInstance(WalletFactoryMiddlewareProjectProposal.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

            jaxbMarshaller.marshal(proposal, System.out);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProposalMarshalUnmarshal3() throws Exception {
        try {
            File file = new File("/home/lnacosta/Desktop/skins.xml");

            RuntimeInlineAnnotationReader.cachePackageAnnotation(WalletFactoryMiddlewareProjectSkin.class.getPackage(), new XmlSchemaMine(""));

            JAXBContext jaxbContext = JAXBContext.newInstance(WalletFactoryMiddlewareProjectSkin.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            WalletFactoryMiddlewareProjectSkin que = (WalletFactoryMiddlewareProjectSkin) jaxbUnmarshaller.unmarshal(file);

            List<WalletFactoryProjectSkin> skins = new ArrayList<>();
            skins.add(que);
            skins.add(que);

            List<WalletFactoryProjectLanguage> languages = new ArrayList<>();
            languages.add(new WalletFactoryMiddlewareProjectLanguage("hungaro.xml"));
            languages.add(new WalletFactoryMiddlewareProjectLanguage("alfredo.xml"));

            WalletFactoryMiddlewareProjectProposal proposal = new WalletFactoryMiddlewareProjectProposal("soyunapropuesta", FactoryProjectState.DISMISSED, skins, languages);

            RuntimeInlineAnnotationReader.cachePackageAnnotation(WalletFactoryMiddlewareProjectProposal.class.getPackage(), new XmlSchemaMine(""));
            jaxbContext = JAXBContext.newInstance(WalletFactoryMiddlewareProjectProposal.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);


            Unmarshaller jaxbunmarshaller213 = jaxbContext.createUnmarshaller();
            WalletFactoryMiddlewareProjectProposal what = (WalletFactoryMiddlewareProjectProposal) jaxbunmarshaller213.unmarshal(new File("/home/lnacosta/Desktop/proposal.xml"));

            System.out.println("*****asd: - " + what.getAlias());

            for (WalletFactoryProjectSkin lan : what.getSkins()) {
                for (WalletFactoryProjectResource sao : lan.getResources()) {
                    System.out.println("*****asd: - " + sao.getName());
                    System.out.println("*****asd: - " + sao.getResourceType());
                }
            }
            Marshaller jaxbmarshaller213 = jaxbContext.createMarshaller();
            jaxbmarshaller213.marshal(what, System.out);


            jaxbMarshaller.marshal(proposal, System.out);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProposalMarshalUnmarshal2() throws Exception {
        try {
            File file = new File("/home/lnacosta/Desktop/proposal.xml");

            RuntimeInlineAnnotationReader.cachePackageAnnotation(WalletFactoryMiddlewareProjectProposal.class.getPackage(), new XmlSchemaMine(""));

            JAXBContext jaxbContext = JAXBContext.newInstance(WalletFactoryMiddlewareProjectProposal.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            WalletFactoryMiddlewareProjectProposal que = (WalletFactoryMiddlewareProjectProposal) jaxbUnmarshaller.unmarshal(file);

            System.out.println("*****asd: - " + que.getAlias());

            for (WalletFactoryProjectLanguage lan : que.getLanguages()) {
                System.out.println("*****asd: - " + lan.getType());
            }

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

            jaxbMarshaller.marshal(que, System.out);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}