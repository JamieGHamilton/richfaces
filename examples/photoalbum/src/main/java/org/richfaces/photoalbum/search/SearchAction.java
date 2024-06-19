/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */


package org.richfaces.photoalbum.search;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import org.richfaces.photoalbum.model.Album;
import org.richfaces.photoalbum.model.Image;
import org.richfaces.photoalbum.model.MetaTag;
import org.richfaces.photoalbum.model.Shelf;
import org.richfaces.photoalbum.model.User;
import org.richfaces.photoalbum.util.Constants;
import org.richfaces.photoalbum.util.PhotoAlbumException;

/**
 * This class is entry point to retrieve search result EJB3 Bean
 *
 * @author Andrey Markhel
 */

@Stateless
@SuppressWarnings("unchecked")
public class SearchAction implements ISearchAction {

    @Inject
    EntityManager em;

    @Inject
    SearchQueryFactory searchQueryFactory;

    @Inject
    private User user;

    /**
     * Return List of albums, founded by query Search albums by name and description(like)
     *
     * @param searchQuery - string to search
     * @param searchInMyAlbums - determine is search will be making by only user's albums
     * @param searchInShared - determine is search will be making in only shared albums
     * @return list of founded albums
     * @throws PhotoAlbumException if search parameter is wrong
     */
    public List<Album> searchByAlbum(String searchQuery, boolean searchInMyAlbums, boolean searchInShared)
        throws PhotoAlbumException {
        Query query = searchQueryFactory.getQuery(SearchEntityEnum.ALBUM, user, searchInShared, searchInMyAlbums, searchQuery);
        if (null == query) {
            throw new PhotoAlbumException(Constants.WRONG_SEARCH_PARAMETERS_ERROR);
        }
        return (List<Album>) query.getResultList();
    }

    /**
     * Return List of images, founded by query Search images by name and description(like)
     *
     * @param searchQuery - string to search
     * @param searchInMyAlbums - determine is search will be making by only user's images
     * @param searchInShared - determine is search will be making in only shared images
     * @return list of founded images
     * @throws PhotoAlbumException if search parameter is wrong
     */
    public List<Image> searchByImage(String searchQuery, boolean searchInMyAlbums, boolean searchInShared)
        throws PhotoAlbumException {
        Query query = searchQueryFactory.getQuery(SearchEntityEnum.IMAGE, user, searchInShared, searchInMyAlbums, searchQuery);
        if (null == query) {
            throw new PhotoAlbumException(Constants.WRONG_SEARCH_PARAMETERS_ERROR);
        }
        return (List<Image>) query.getResultList();
    }

    /**
     * Return List of users, founded by query Search users by login, firstname and secondname(like)
     *
     * @param searchQuery - string to search
     * @param searchInMyAlbums - unused
     * @param searchInShared - unused
     * @return list of founded users
     * @throws PhotoAlbumException if search parameter is wrong
     */
    public List<User> searchByUsers(String searchQuery, boolean searchInMyAlbums, boolean searchInShared)
        throws PhotoAlbumException {
        Query query = searchQueryFactory.getQuery(SearchEntityEnum.USER, user, searchInShared, searchInMyAlbums, searchQuery);
        if (null == query) {
            throw new PhotoAlbumException(Constants.WRONG_SEARCH_PARAMETERS_ERROR);
        }
        return (List<User>) query.getResultList();
    }

    /**
     * Return List of metatags, founded by query Search users by tagname(like)
     *
     * @param searchQuery - string to search
     * @param searchInMyAlbums - unused
     * @param searchInShared - unused
     * @return list of founded metatags
     * @throws PhotoAlbumException if search parameter is wrong
     */
    public List<MetaTag> searchByTags(String searchQuery, boolean searchInMyAlbums, boolean searchInShared)
        throws PhotoAlbumException {
        Query query = searchQueryFactory
            .getQuery(SearchEntityEnum.METATAG, user, searchInShared, searchInMyAlbums, searchQuery);
        if (null == query) {
            throw new PhotoAlbumException(Constants.WRONG_SEARCH_PARAMETERS_ERROR);
        }
        return (List<MetaTag>) query.getResultList();
    }

    /**
     * Return List of shelves, founded by query Search images by name and description(like)
     *
     * @param searchQuery - string to search
     * @param searchInMyAlbums - determine is search will be making by only user's shelves
     * @param searchInShared - determine is search will be making in only shared shelves
     * @return list of founded images
     * @throws PhotoAlbumException if search parameter is wrong
     */
    public List<Shelf> searchByShelves(String searchQuery, boolean searchInMyAlbums, boolean searchInShared)
        throws PhotoAlbumException {
        Query query = searchQueryFactory.getQuery(SearchEntityEnum.SHELF, user, searchInShared, searchInMyAlbums, searchQuery);
        if (null == query) {
            throw new PhotoAlbumException(Constants.WRONG_SEARCH_PARAMETERS_ERROR);
        }
        return (List<Shelf>) query.getResultList();
    }

}