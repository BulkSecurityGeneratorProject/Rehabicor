/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { PatientService } from 'app/entities/patient/patient.service';
import { IPatient, Patient } from 'app/shared/model/patient.model';

describe('Service Tests', () => {
    describe('Patient Service', () => {
        let injector: TestBed;
        let service: PatientService;
        let httpMock: HttpTestingController;
        let elemDefault: IPatient;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(PatientService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new Patient(0, 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA', 0, false, false, false, 0, 0, false, 'AAAAAAA');
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Patient', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new Patient(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Patient', async () => {
                const returnedFromService = Object.assign(
                    {
                        code: 'BBBBBB',
                        age: 1,
                        sex: 'BBBBBB',
                        ocupation: 'BBBBBB',
                        lastEventOcurred: 1,
                        deceased: true,
                        abandonment: true,
                        abandonmentMedicCause: true,
                        rehabStatus: 1,
                        sessionNumber: 1,
                        deleted: true,
                        scholarship: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Patient', async () => {
                const returnedFromService = Object.assign(
                    {
                        code: 'BBBBBB',
                        age: 1,
                        sex: 'BBBBBB',
                        ocupation: 'BBBBBB',
                        lastEventOcurred: 1,
                        deceased: true,
                        abandonment: true,
                        abandonmentMedicCause: true,
                        rehabStatus: 1,
                        sessionNumber: 1,
                        deleted: true,
                        scholarship: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a Patient', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
