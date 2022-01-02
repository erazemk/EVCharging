import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

/*import { NakupovalniSeznam } from './models/seznam';
import { SeznamiService } from './services/seznami.service';*/
import { Owner } from './models/owner';
import { Station } from './models/station';
import { UserService } from './services/user.service';

@Component({
    moduleId: module.id,
    selector: 'all-users',
    templateUrl: 'user.component.html'
})
export class UserComponent implements OnInit {
    stations: Station[];
    station: Station;

    constructor(private userService: UserService,
                private router: Router) {
    }

    ngOnInit(): void {
        this.getUsers();
    }

    getUsers(): void {
        this.userService
            .getStations()
            .subscribe(stations => this.stations = stations);
    }

    details(station: Station): void {
        this.station = station;
        this.router.navigate(['/stations', this.station.id]);
    }

    delete(station: Station): void {
        this.userService
            .delete(station.id)
            .subscribe(stationId => this.stations = this.stations.filter(s => s.id !== stationId));
    }

}
