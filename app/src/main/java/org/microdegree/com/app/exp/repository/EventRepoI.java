package org.microdegree.com.app.exp.repository;

import androidx.lifecycle.LiveData;

import org.microdegree.com.app.exp.vo.CourseModel;


import java.util.List;
import java.util.Map;

/**
 * Created by Rakesh on 1/20/18.
 */

public interface EventRepoI {


/*
    LiveData<List<ChannelModel>> getChannelList(String header, Map<String,Object> searchQueryMap);
*/

    LiveData<List<CourseModel>> getCoursesList(String header, Map<String,Object> searchQueryMap);


  /*  LiveData<List<ChannelModel>> getChannelsByPackageId(String packageId);

    LiveData<List<ChannelModel>> getPackageListByBroadcasterId(String broadcasterId);*/

        void runInitialObservables();

    /*LiveData<List<ChannelModel>> getAllChannelsByIdSet(List<String> channelIdSet);

    LiveData<List<ChannelModel>> getFreeChannelsForCableOpPackageId(String cableOpPackageId);

    LiveData<List<ChannelModel>> getBroadCasterPackagesForCableOpPackageId(String cableOpPackageId);*/

/*
    LiveData<List<String>> getChannelIdsByPackageIdSet(Set<String> packageIdList);
*/

/*
    LiveData<CableOpPackage> getCableOpPackageById(String cableOpPackageId);
*/


    }
