package org.yearup.data;


import org.springframework.stereotype.Repository;
import org.yearup.models.Profile;

import java.util.List;

@Repository
public interface ProfileDao
{
    Profile create(Profile profile);

    List<Profile> getAll();

    Profile getByUserId(int userId);

    void update(int userId, Profile profile);
}
