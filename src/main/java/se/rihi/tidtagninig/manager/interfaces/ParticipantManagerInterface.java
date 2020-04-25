package se.rihi.tidtagninig.manager.interfaces;

import se.rihi.tidtagninig.entity.Participant;

import java.util.List;

public interface ParticipantManagerInterface {

    /**
     * load Hibernate Session factory
     */
    void setup();

    /**
     * close Hibernate Session factory
     */
    void exit();

    /**
     * create a new Participant
     * @param participant participant to be added to DB
     */
    void create(Participant participant);

    /**
     * get all Participants
     * @return
     */
    List<Participant> read();

    /**
     * Find a Participant
     * @param namerQuery name of the query
     * @param searchTerm what to search for
     * @return a single Participant
     */
    Participant findById(String namerQuery, int searchTerm);

    /**
     * Find many Participants
     * @param namedQuery name of the query
     * @param searchTerm what to search for
     * @return a list of Participants
     */
    List<Participant> findByName(String namedQuery, String searchTerm);

    void update(Participant participant);

    void delete();
}
