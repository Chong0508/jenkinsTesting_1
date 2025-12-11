package com.cms.repository;

import com.cms.model.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Long> {
    // --- Basic CRUD (Automatic) ---
    // save(), findById(), findAll(), deleteById()
    // are all included automatically.


    // --- Custom Finders ---

    /*
     * Finds a single conference by its exact acronym (e.g., "ICSE").
     * SQL: "SELECT * FROM conference WHERE Acronym = ?"
     * @param acronym The exact acronym to search for.
     * @return An Optional<Conference> which might be empty if not found.
     */
    Optional<Conference> findByAcronym(String acronym);


    /*
     * Finds all conferences held in a specific year.
     * SQL: "SELECT * FROM conference WHERE Year = ?"
     * @param year The year to search for.
     * @return A List of conferences (could be empty).
     */
    List<Conference> findByYear(Integer year);


    /*
     * Finds conferences whose names contain a specific keyword (case-sensitive).
     * SQL: "SELECT * FROM conference WHERE Name LIKE '%keyword%'"
     * @param keyword The text to search for within the conference name.
     * @return A List of matching conferences.
     */
    List<Conference> findByNameContaining(String keyword);


    /*
     * Finds conferences that start *after* a specific date.
     * SQL: "SELECT * FROM conference WHERE Start_date > ?"
     * @param date The date to compare against.
     * @return A List of conferences starting after the given date.
     */
    List<Conference> findByStartDateAfter(LocalDate date);


    /*
     * Finds conferences that end *before* a specific date.
     * SQL: "SELECT * FROM conference WHERE End_date < ?"
     * @param date The date to compare against.
     * @return A List of conferences ending before the given date.
     */
    List<Conference> findByEndDateBefore(LocalDate date);


    /*
     * Finds conferences that start between two dates (inclusive).
     * SQL: "SELECT * FROM conference WHERE Start_date BETWEEN ? AND ?"
     * @param startDate The beginning of the date range.
     * @param endDate The end of the date range.
     * @return A List of conferences within the date range.
     */
    List<Conference> findByStartDateBetween(LocalDate startDate, LocalDate endDate);


    /*
     * Finds conferences by both their acronym AND year.
     * SQL: "SELECT * FROM conference WHERE Acronym = ? AND Year = ?"
     * @param acronym The conference acronym.
     * @param year The conference year.
     * @return An Optional<Conference>
     */
    Optional<Conference> findByAcronymAndYear(String acronym, Integer year);

}
