import { Time } from "@angular/common";

export class Station {
    id: number;
    name: String;
    ownerId: number;
    openTime: Time;
    closeTime: Time;
    price: Float32Array;
    wattage: number;
    adapterType: String;
    locationId: number;
}