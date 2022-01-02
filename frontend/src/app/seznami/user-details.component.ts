import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {Location} from '@angular/common';

import { switchMap } from 'rxjs/operators';

import { Owner } from './models/owner';
import { UserService } from './services/user.service';
import { Station } from './models/station';

@Component({
    moduleId: module.id,
    selector: 'user-details',
    templateUrl: 'user-details.component.html'
})
export class UserDetailsComponent implements OnInit {
    owner: Owner;
    station: Station;

    constructor(private userService: UserService,
                private route: ActivatedRoute,
                private location: Location,
                private router: Router) {
    }

    ngOnInit(): void {
        this.route.params.pipe(
            switchMap((params: Params) => this.userService.getStation(+params['id'])))
            .subscribe(station => {
                this.station = station;
                this.getOwner()});    
        
        
    }
    getOwner(): void{
        this.userService.getOwner(this.station.ownerId).subscribe(owner => this.owner = owner);
    }
    addStation(): void {
        this.router.navigate(['stations/' + this.station.id + '/add']);
    }

    back(): void {
        this.router.navigate(['stations']);
    }
}
