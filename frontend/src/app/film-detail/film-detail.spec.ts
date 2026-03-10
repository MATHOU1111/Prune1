import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilmDetail } from './film-detail';

describe('FilmDetail', () => {
  let component: FilmDetail;
  let fixture: ComponentFixture<FilmDetail>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FilmDetail],
    }).compileComponents();

    fixture = TestBed.createComponent(FilmDetail);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
