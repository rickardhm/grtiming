package se.rihi.tidtagninig.entity;

import org.junit.Assert;
import org.junit.Test;
import se.rihi.tidtagninig.support.AssertAnnotations;
import se.rihi.tidtagninig.support.ReflectTool;

import javax.persistence.*;

public class ParticipantTest {

    @Test
    public void typeAnnotations() {
        // assert
        AssertAnnotations.assertType(Participant.class, NamedQueries.class, Entity.class, Table.class);
    }

    @Test

    public void entity() {
        // setup
        Entity a = ReflectTool.getClassAnnotation(Participant.class, Entity.class);
        // assert
        Assert.assertEquals("", a.name());
    }

    @Test
    public void table() {
        // setup
        Table t = ReflectTool.getClassAnnotation(Participant.class, Table.class);
        // assert
        Assert.assertEquals("participant", t.name());
    }

    @Test
    public void fieldAnnotations() {
        // assert
        AssertAnnotations.assertField(Participant.class, "id", Id.class, GeneratedValue.class);
        AssertAnnotations.assertField(Participant.class, "firstName", Column.class);
        AssertAnnotations.assertField(Participant.class, "lastName", Column.class);
        AssertAnnotations.assertField(Participant.class, "email", Column.class);
        AssertAnnotations.assertField(Participant.class, "phone", Column.class);
        AssertAnnotations.assertField(Participant.class, "club", Column.class);
        AssertAnnotations.assertField(Participant.class, "age", Column.class);
        AssertAnnotations.assertField(Participant.class, "sex", Column.class);
    }

    @Test
    public void methodAnnotations() {
        // assert
        AssertAnnotations.assertMethod(Participant.class, "getFirstName");
        AssertAnnotations.assertMethod(Participant.class, "getLastName");
        AssertAnnotations.assertMethod(Participant.class, "getEmail");
        AssertAnnotations.assertMethod(Participant.class, "getPhone");
        AssertAnnotations.assertMethod(Participant.class, "getClub");
        AssertAnnotations.assertMethod(Participant.class, "getAge");
        AssertAnnotations.assertMethod(Participant.class, "getSex");
    }

}