import {FuseNavigation} from '../../../@fuse/types';

export const navigation: FuseNavigation[] = [
    {
        id: 'map',
        title: 'Map',
        translate: 'Map',
        type: 'item',
        icon: 'map',
        url: '/map'
    },
    {
        id: 'companies',
        title: 'Companies',
        translate: 'Companies',
        type: 'item',
        icon: 'business',
        url: '/companies'
    },

    {
        id: 'history',
        title: 'History',
        translate: 'History',
        type: 'item',
        icon: 'history',
        url: '/history'
    }


];
